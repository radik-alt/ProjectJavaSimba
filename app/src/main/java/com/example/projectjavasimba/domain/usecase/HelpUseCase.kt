package com.example.projectjavasimba.domain.usecase

import android.content.Context
import com.example.projectjavasimba.domain.entity.CategoriesEntity
import kotlinx.coroutines.flow.Flow

interface HelpUseCase {
    suspend fun getCategory(context: Context, newSession: Boolean): Flow<CategoriesEntity>
}