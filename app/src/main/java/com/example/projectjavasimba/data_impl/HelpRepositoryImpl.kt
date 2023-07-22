package com.example.projectjavasimba.data_impl

import android.content.Context
import android.util.Log
import com.example.projectjavasimba.data.repository.HelpRepository
import com.example.projectjavasimba.domain.entity.CategoriesEntity
import com.example.projectjavasimba.domain.entity.CategoryEntity
import com.example.projectjavasimba.repository.api.RetrofitBuilder
import com.example.projectjavasimba.repository.dto.categories.CategoriesDto
import com.example.projectjavasimba.repository.dto.categories.CategoryDto
import io.reactivex.rxjava3.core.Observable

class HelpRepositoryImpl : HelpRepository {

    private val api = RetrofitBuilder.apiService

    override fun getCategory(context: Context): Observable<CategoriesEntity> {
        return api.getCategories()
            .doOnError {
                Log.d("GetError", it.message.toString())
            }
            .flatMap {
                Observable.just(it.toEntity())
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