package com.example.projectjavasimba.data.repository

import android.content.Context
import com.example.projectjavasimba.domain.entity.EventsEntity
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getEvents(context: Context): Flow<EventsEntity>
    suspend fun getCacheEvents(context: Context): Flow<EventsEntity>
}