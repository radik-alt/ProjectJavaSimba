package com.example.projectjavasimba.domain.usecase

import android.content.Context
import com.example.core.entity.EventsEntity
import kotlinx.coroutines.flow.Flow

interface MainUseCase {
    suspend  fun getEvents(context: Context, newSession: Boolean): Flow<EventsEntity>
    suspend fun getCacheEvents(context: Context): Flow<EventsEntity>
}