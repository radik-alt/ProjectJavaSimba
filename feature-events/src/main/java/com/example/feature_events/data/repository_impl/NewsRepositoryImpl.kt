package com.example.feature_events.data.repository_impl

import android.content.Context
import android.util.Log
import com.example.core.entity.EventsEntity
import com.example.core.repository.api.PostmanApi
import com.example.core.repository.db.SimbaDataBase
import com.example.core.repository.db.dto.PhotoRoomDto
import com.example.core.repository.dto.events.EventDto
import com.example.feature_events.data.mapping.toEntity
import com.example.feature_events.data.mapping.toEventEntityList
import com.example.feature_events.data.mapping.toRoomDto
import com.example.feature_events.data.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val db: SimbaDataBase,
    private val api: PostmanApi
) : NewsRepository {

    override suspend fun getEvents(context: Context, newSession: Boolean): Flow<EventsEntity> {
        return if (!newSession) {
            getCacheEvents(context)
        } else {
            val response = api.getEvents()
            response.eventDtos?.let { insertCacheEvents(it) }
            flowOf(response).map {
                it.toEntity()
            }.catch {
//                emit(
//                    EventsEntity(
//                        MyCallableEvent(context).call().toList()
//                    )
//                )
            }
        }
    }

    override suspend fun getCacheEvents(context: Context): Flow<EventsEntity> {
        return db.eventsDao.select()
            .onEmpty {
                getEvents(context, true)
            }
            .map {
                EventsEntity(it.toEventEntityList())
            }.catch {
                getEvents(context, true)
            }
    }

    private suspend fun insertCacheEvents(events: List<EventDto>) {
        db.eventsDao.delete()
        events.forEach { dto ->
            val id = db.eventsDao.insertEvent(dto.toRoomDto())
            dto.photos?.map { image ->
                val photoDto = PhotoRoomDto(
                    id = null,
                    eventId = id.toInt(),
                    photoUrl = image
                )
                db.eventsDao.insertPhoto(photoDto)
            }
        }
    }

}