package com.example.projectjavasimba.domain.usecase

import android.content.Context
import com.example.projectjavasimba.domain.entity.EventsEntity
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    suspend  fun getEvents(context: Context, newSession: Boolean): Flow<EventsEntity>
    suspend fun getCacheEvents(context: Context): Flow<EventsEntity>
}