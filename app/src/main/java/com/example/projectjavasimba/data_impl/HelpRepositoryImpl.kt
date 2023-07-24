package com.example.projectjavasimba.data_impl

import android.content.Context
import com.example.projectjavasimba.data.repository.HelpRepository
import com.example.projectjavasimba.data_impl.callable.MyCallableCategory
import com.example.projectjavasimba.domain.entity.CategoriesEntity
import com.example.projectjavasimba.domain.entity.CategoryEntity
import com.example.projectjavasimba.repository.api.RetrofitBuilder
import com.example.projectjavasimba.repository.dto.categories.CategoriesDto
import com.example.projectjavasimba.repository.dto.categories.CategoryDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class HelpRepositoryImpl : HelpRepository {

    private val api = RetrofitBuilder.apiService

    override suspend fun getCategory(context: Context): Flow<CategoriesEntity> {
        return flowOf(api.getCategories()).map {
            it.toEntity()
        }.catch {
            emit(
                MyCallableCategory(
                    context
                ).call().toEntity()
            )
        }
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