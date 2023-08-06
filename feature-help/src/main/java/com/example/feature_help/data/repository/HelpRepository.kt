package com.example.feature_help.data.repository

import android.content.Context
import com.example.core.entity.CategoriesEntity
import kotlinx.coroutines.flow.Flow

interface HelpRepository {
    suspend fun getCategory(context: Context, newSession: Boolean): Flow<CategoriesEntity>
}