package com.example.feature_events.data.repository

import android.content.Context
import com.example.core.entity.CategoriesEntity
import kotlinx.coroutines.flow.Flow

interface FilterRepository {
    suspend fun getCategory(context: Context, newSession: Boolean): Flow<CategoriesEntity>
}