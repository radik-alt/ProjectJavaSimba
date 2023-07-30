package com.example.projectjavasimba.data_impl

import android.content.Context
import android.util.Log
import com.example.projectjavasimba.data_impl.callable.MyCallableEvent
import com.example.projectjavasimba.data.repository.NewsRepository
import com.example.projectjavasimba.domain.entity.EventEntity
import com.example.projectjavasimba.domain.entity.EventsEntity
import com.example.repository.api.PostmanApi
import com.example.repository.db.SimbaDataBase
import com.example.repository.db.dto.EventWitPhotos
import com.example.repository.db.dto.EventsRoomDto
import com.example.repository.db.dto.PhotoRoomDto
import com.example.repository.dto.events.EventDto
import com.example.repository.dto.events.EventsDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import java.util.Date
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val db: com.example.repository.db.SimbaDataBase,
    private val api: com.example.repository.api.PostmanApi
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
                emit(
                    EventsEntity(
                        MyCallableEvent(context).call().toList()
                    )
                )
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

    private fun List<com.example.repository.db.dto.EventWitPhotos>.toEventEntityList() = map {
        EventEntity(
            id = it.event?.id ?: -1,
            title = it.event?.name ?: "",
            description = it.event?.description ?: "",
            listImage = it.eventPhotos?.map { image -> image.photoUrl } ?: arrayListOf(),
            createAt = it.event?.createAt ?: Date(),
            street = it.event?.address ?: "",
            status = it.event?.status ?: -1,
            startDate = it.event?.startDate ?: Date(),
            endDate = it.event?.endDate ?: Date(),
            phone = it.event?.phone ?: "",
            category = it.event?.category ?: -1,
            isRead = false,
        )
    }

    private suspend fun insertCacheEvents(events: List<com.example.repository.dto.events.EventDto>) {
        db.eventsDao.delete()
        events.forEach { dto ->
            val id = db.eventsDao.insertEvent(dto.toRoomDto())
            Log.d("GetIndex", id.toString())
            dto.photos?.map { image ->
                val photoDto = com.example.repository.db.dto.PhotoRoomDto(
                    id = null,
                    eventId = id.toInt(),
                    photoUrl = image
                )
                db.eventsDao.insertPhoto(photoDto)
            }
        }
    }

    private fun com.example.repository.dto.events.EventDto.toRoomDto() =
        com.example.repository.db.dto.EventsRoomDto(
            id = this.id ?: -1,
            name = this.name ?: "",
            description = this.description ?: "",
            createAt = this.createAt ?: Date(),
            address = this.address ?: "",
            status = this.status ?: -1,
            startDate = this.startDate ?: Date(),
            endDate = this.endDate ?: Date(),
            phone = this.phone ?: "",
            category = this.category ?: -1,
            organisation = this.organisation ?: ""
        )

    private fun List<com.example.repository.dto.events.EventDto>.toRoomEntityList() = map {
        com.example.repository.db.dto.EventWitPhotos(
            event = com.example.repository.db.dto.EventsRoomDto(
                id = it.id ?: -1,
                name = it.name ?: "",
                description = it.description ?: "",
                createAt = it.createAt ?: Date(),
                address = it.address ?: "",
                status = it.status ?: -1,
                startDate = it.startDate ?: Date(),
                endDate = it.endDate ?: Date(),
                phone = it.phone ?: "",
                category = it.category ?: -1,
                organisation = it.organisation ?: ""
            ),
            eventPhotos = listOf<com.example.repository.db.dto.PhotoRoomDto>().apply {
                it.photos?.forEach { image ->
                    com.example.repository.db.dto.PhotoRoomDto(
                        id = it.id ?: -1,
                        eventId = it.id ?: -1,
                        photoUrl = image
                    )
                }
            }
        )
    }

    private fun com.example.repository.dto.events.EventsDto.toEntity() = EventsEntity(
        events = this.eventDtos?.toEntityList() ?: arrayListOf()
    )

    private fun List<com.example.repository.dto.events.EventDto>.toEntityList() = map {
        EventEntity(
            id = it.id ?: -1,
            title = it.name ?: "",
            description = it.description ?: "",
            listImage = it.photos ?: arrayListOf(),
            createAt = it.createAt ?: Date(),
            street = it.address ?: "",
            status = it.status ?: -1,
            startDate = it.startDate ?: Date(),
            endDate = it.endDate ?: Date(),
            phone = it.phone ?: "",
            category = it.category ?: -1,
            isRead = false,
        )
    }


}