package com.example.projectjavasimba.data.repository_impl

import android.content.Context
import android.util.Log
import com.example.core.entity.EventEntity
import com.example.core.entity.EventsEntity
import com.example.core.repository.api.PostmanApi
import com.example.core.repository.db.SimbaDataBase
import com.example.core.repository.db.dto.EventWitPhotos
import com.example.core.repository.db.dto.EventsRoomDto
import com.example.core.repository.db.dto.PhotoRoomDto
import com.example.core.repository.dto.events.EventDto
import com.example.core.repository.dto.events.EventsDto
import com.example.projectjavasimba.data.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import java.util.Date
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val db: SimbaDataBase,
    private val api: PostmanApi
) : MainRepository {

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

    private fun List<EventWitPhotos>.toEventEntityList() = map {
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

    private suspend fun insertCacheEvents(events: List<EventDto>) {
        db.eventsDao.delete()
        events.forEach { dto ->
            val id = db.eventsDao.insertEvent(dto.toRoomDto())
            Log.d("GetIndex", id.toString())
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

    private fun EventDto.toRoomDto() = EventsRoomDto(
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

    private fun List<EventDto>.toRoomEntityList() = map {
        EventWitPhotos(
            event = EventsRoomDto(
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
            eventPhotos = listOf<PhotoRoomDto>().apply {
                it.photos?.forEach { image ->
                    PhotoRoomDto(
                        id = it.id ?: -1,
                        eventId = it.id ?: -1,
                        photoUrl = image
                    )
                }
            }
        )
    }

    private fun EventsDto.toEntity() = EventsEntity(
        events = this.eventDtos?.toEntityList() ?: arrayListOf()
    )

    private fun List<EventDto>.toEntityList() = map {
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