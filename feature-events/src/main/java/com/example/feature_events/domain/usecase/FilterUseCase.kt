package com.example.feature_events.domain.usecase

import android.content.Context
import com.example.core.entity.CategoriesEntity
import kotlinx.coroutines.flow.Flow

interface FilterUseCase {
    suspend fun getCategory(context: Context, newSession: Boolean): Flow<CategoriesEntity>
}