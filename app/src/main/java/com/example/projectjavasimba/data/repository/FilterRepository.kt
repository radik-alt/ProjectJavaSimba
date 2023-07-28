package com.example.projectjavasimba.data.repository

import android.content.Context
import com.example.projectjavasimba.domain.entity.CategoriesEntity
import kotlinx.coroutines.flow.Flow

interface FilterRepository {

    suspend fun getCategory(context: Context, newSession: Boolean): Flow<CategoriesEntity>
}