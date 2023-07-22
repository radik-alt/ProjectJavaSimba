package com.example.projectjavasimba.domain_impl.interactor

import android.content.Context
import com.example.projectjavasimba.data.repository.HelpRepository
import com.example.projectjavasimba.domain.entity.CategoriesEntity
import com.example.projectjavasimba.domain.usecase.HelpUseCase
import io.reactivex.rxjava3.core.Observable

class HelpInteractor(
    private val repository: HelpRepository
) : HelpUseCase {
    override fun getCategory(context: Context): Observable<CategoriesEntity> =
        repository.getCategory(context)
}