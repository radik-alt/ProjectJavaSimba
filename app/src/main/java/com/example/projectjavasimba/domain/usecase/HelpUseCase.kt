package com.example.projectjavasimba.domain.usecase

import android.content.Context
import com.example.projectjavasimba.domain.entity.CategoriesEntity
import io.reactivex.rxjava3.core.Observable

interface HelpUseCase {
    fun getCategory(context: Context): Observable<CategoriesEntity>
}