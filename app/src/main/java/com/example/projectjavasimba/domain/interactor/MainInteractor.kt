package com.example.projectjavasimba.domain.interactor

import android.content.Context
import com.example.core.entity.EventsEntity
import com.example.projectjavasimba.data.repository.MainRepository
import com.example.projectjavasimba.domain.usecase.MainUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val repository: MainRepository
) : MainUseCase {
    override suspend fun getEvents(context: Context, newSession: Boolean) =
        repository.getEvents(context, newSession)

    override suspend fun getCacheEvents(context: Context): Flow<EventsEntity> =
        repository.getCacheEvents(context)
}