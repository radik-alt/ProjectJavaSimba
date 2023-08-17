package com.example.feature_events.domain.usecase

import android.content.Context
import com.example.core.entity.EventsEntity
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    suspend  fun getEvents(context: Context, newSession: Boolean): Flow<EventsEntity>
    suspend fun getCacheEvents(context: Context): Flow<EventsEntity>
}