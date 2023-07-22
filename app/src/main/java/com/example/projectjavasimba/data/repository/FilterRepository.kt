package com.example.projectjavasimba.data.repository

import android.content.Context
import com.example.projectjavasimba.domain.entity.CategoriesEntity
import io.reactivex.rxjava3.core.Observable

interface FilterRepository {

    fun getCategory(context: Context): Observable<CategoriesEntity>
}