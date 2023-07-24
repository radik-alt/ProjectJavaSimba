package com.example.projectjavasimba.domain.usecase

import android.content.Context
import com.example.projectjavasimba.domain.entity.CategoriesEntity
import kotlinx.coroutines.flow.Flow

interface FilterUseCase {
    suspend fun getCategory(context: Context): Flow<CategoriesEntity>
}