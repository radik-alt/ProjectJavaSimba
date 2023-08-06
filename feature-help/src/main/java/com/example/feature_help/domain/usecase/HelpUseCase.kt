package com.example.feature_help.domain.usecase

import android.content.Context
import com.example.core.entity.CategoriesEntity
import kotlinx.coroutines.flow.Flow

interface HelpUseCase {
    suspend fun getCategory(context: Context, newSession: Boolean): Flow<CategoriesEntity>
}