package com.example.feature_events.domain.interactor

import android.content.Context
import com.example.core.entity.EventsEntity
import com.example.feature_events.data.repository.NewsRepository
import com.example.feature_events.domain.usecase.NewsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsInteractor @Inject constructor(
    private val repository: NewsRepository
) : NewsUseCase {
    override suspend fun getEvents(context: Context, newSession: Boolean) =
        repository.getEvents(context, newSession)

    override suspend fun getCacheEvents(context: Context): Flow<EventsEntity> =
        repository.getCacheEvents(context)
}