package com.example.projectjavasimba.domain_impl.interactor

import android.content.Context
import com.example.projectjavasimba.data.repository.NewsRepository
import com.example.projectjavasimba.domain.entity.EventsEntity
import com.example.projectjavasimba.domain.usecase.NewsUseCase
import kotlinx.coroutines.flow.Flow

class NewsInteractor(
    private val repository: NewsRepository
) : NewsUseCase {
    override suspend fun getEvents(context: Context) =
        repository.getEvents(context)

    override suspend fun getCacheEvents(context: Context): Flow<EventsEntity> =
        repository.getCacheEvents(context)
}