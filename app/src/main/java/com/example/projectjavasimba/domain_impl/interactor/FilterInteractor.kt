package com.example.projectjavasimba.domain_impl.interactor

import android.content.Context
import com.example.projectjavasimba.data.repository.FilterRepository
import com.example.projectjavasimba.domain.entity.CategoriesEntity
import com.example.projectjavasimba.domain.usecase.FilterUseCase
import io.reactivex.rxjava3.core.Observable

class FilterInteractor(
    private val repository: FilterRepository
): FilterUseCase {
    override fun getCategory(context: Context): Observable<CategoriesEntity> =
        repository.getCategory(context)
}