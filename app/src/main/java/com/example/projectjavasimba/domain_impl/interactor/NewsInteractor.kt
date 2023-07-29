package com.example.projectjavasimba.domain_impl.interactor

import android.content.Context
import com.example.projectjavasimba.data.repository.NewsRepository
import com.example.projectjavasimba.domain.entity.EventsEntity
import com.example.projectjavasimba.domain.usecase.NewsUseCase
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