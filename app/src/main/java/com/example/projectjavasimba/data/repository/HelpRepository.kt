package com.example.projectjavasimba.data.repository

import android.content.Context
import com.example.projectjavasimba.domain.entity.CategoriesEntity
import kotlinx.coroutines.flow.Flow

interface HelpRepository {

    suspend fun getCategory(context: Context): Flow<CategoriesEntity>
}