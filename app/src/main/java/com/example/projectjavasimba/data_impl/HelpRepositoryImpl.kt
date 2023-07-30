package com.example.projectjavasimba.data_impl

import android.content.Context
import com.example.projectjavasimba.data.repository.HelpRepository
import com.example.projectjavasimba.data_impl.callable.MyCallableCategory
import com.example.projectjavasimba.domain.entity.CategoriesEntity
import com.example.projectjavasimba.domain.entity.CategoryEntity
import com.example.repository.api.PostmanApi
import com.example.repository.api.RetrofitBuilder
import com.example.repository.db.SimbaDataBase
import com.example.repository.db.dto.CategoryRoomDto
import com.example.repository.dto.categories.CategoriesDto
import com.example.repository.dto.categories.CategoryDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HelpRepositoryImpl @Inject constructor(
    private val db: com.example.repository.db.SimbaDataBase,
    private val api: com.example.repository.api.PostmanApi
) : HelpRepository {

    override suspend fun getCategory(context: Context, newSession: Boolean): Flow<CategoriesEntity> {
        if (!newSession) {
            return getCacheCategory()
        } else {
            val response = api.getCategories()
            response.categories?.let { insertCacheCategory(it.toEntityList()) }

            return flowOf(response).map {
                it.toEntity()
            }.catch {
                emit(
                    MyCallableCategory(
                        context
                    ).call().toEntity()
                )
            }
        }
    }

    private fun getCacheCategory(): Flow<CategoriesEntity> {
        return db.categoriesDao.select()
            .map {
                CategoriesEntity(it.roomToEntityList())
            }.catch {

            }
    }

    private suspend fun insertCacheCategory(list: List<CategoryEntity>) {
        db.categoriesDao.delete()
        db.categoriesDao.insert(list.toRoomEntityList())
    }

    private fun List<CategoryEntity>.toRoomEntityList() = map {
        com.example.repository.db.dto.CategoryRoomDto(
            id = it.id ?: -1,
            image = it.image ?: "",
            name = it.name ?: "",
            nameEn = it.nameEn ?: ""
        )
    }

    private fun List<com.example.repository.db.dto.CategoryRoomDto>.roomToEntityList() = map {
        CategoryEntity(
            id = it.id ?: -1,
            image = it.image ?: "",
            name = it.name ?: "",
            nameEn = it.nameEn ?: ""
        )
    }

    private fun com.example.repository.dto.categories.CategoriesDto.toEntity() = CategoriesEntity(
        categories = this.categories?.toEntityList() ?: arrayListOf()
    )

    private fun List<com.example.repository.dto.categories.CategoryDto>.toEntityList() = map {
        CategoryEntity(
            id = it.id ?: -1,
            image = it.image ?: "",
            name = it.name ?: "",
            nameEn = it.nameEn ?: ""
        )
    }

}