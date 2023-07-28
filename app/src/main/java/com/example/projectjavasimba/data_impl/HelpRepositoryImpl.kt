package com.example.projectjavasimba.data_impl

import android.content.Context
import com.example.projectjavasimba.data.repository.HelpRepository
import com.example.projectjavasimba.data_impl.callable.MyCallableCategory
import com.example.projectjavasimba.domain.entity.CategoriesEntity
import com.example.projectjavasimba.domain.entity.CategoryEntity
import com.example.projectjavasimba.repository.api.RetrofitBuilder
import com.example.projectjavasimba.repository.db.SimbaDataBase
import com.example.projectjavasimba.repository.db.dto.CategoryRoomDto
import com.example.projectjavasimba.repository.dto.categories.CategoriesDto
import com.example.projectjavasimba.repository.dto.categories.CategoryDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class HelpRepositoryImpl(
    private val db: SimbaDataBase
) : HelpRepository {

    private val api = RetrofitBuilder.apiService

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
        CategoryRoomDto(
            id = it.id ?: -1,
            image = it.image ?: "",
            name = it.name ?: "",
            nameEn = it.nameEn ?: ""
        )
    }

    private fun List<CategoryRoomDto>.roomToEntityList() = map {
        CategoryEntity(
            id = it.id ?: -1,
            image = it.image ?: "",
            name = it.name ?: "",
            nameEn = it.nameEn ?: ""
        )
    }

    private fun CategoriesDto.toEntity() = CategoriesEntity(
        categories = this.categories?.toEntityList() ?: arrayListOf()
    )

    private fun List<CategoryDto>.toEntityList() = map {
        CategoryEntity(
            id = it.id ?: -1,
            image = it.image ?: "",
            name = it.name ?: "",
            nameEn = it.nameEn ?: ""
        )
    }

}