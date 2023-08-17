package com.example.feature_events.data.repository_impl

import android.content.Context
import com.example.core.entity.CategoriesEntity
import com.example.core.entity.CategoryEntity
import com.example.core.repository.api.PostmanApi
import com.example.core.repository.db.SimbaDataBase
import com.example.core.repository.db.dto.CategoryRoomDto
import com.example.core.repository.dto.categories.CategoriesDto
import com.example.core.repository.dto.categories.CategoryDto
import com.example.feature_events.data.repository.FilterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FilterRepositoryImpl @Inject constructor(
    private val db: SimbaDataBase,
    private val api: PostmanApi
) : FilterRepository {

    override suspend fun getCategory(context: Context, newSession: Boolean): Flow<CategoriesEntity> {
        return if (!newSession) {
            getCacheCategory()
        } else {
            flowOf(api.getCategories()).map {
                it.toEntity()
            }.catch {
//                emit(
//                    MyCallableCategory(
//                        context
//                    ).call().toEntity()
//                )
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