package com.example.feature_events.data.mapping

import com.example.core.entity.EventEntity
import com.example.core.entity.EventsEntity
import com.example.core.repository.db.dto.EventWitPhotos
import com.example.core.repository.db.dto.EventsRoomDto
import com.example.core.repository.db.dto.PhotoRoomDto
import com.example.core.repository.dto.events.EventDto
import com.example.core.repository.dto.events.EventsDto
import java.util.Date

fun EventWitPhotos?.toEventEntity() = EventEntity(
    id = this?.event?.id ?: -1,
    title = this?.event?.name ?: "",
    description = this?.event?.description ?: "",
    listImage = this?.eventPhotos?.map { image -> image.photoUrl } ?: arrayListOf(),
    createAt = this?.event?.createAt ?: Date(),
    street = this?.event?.address ?: "",
    status = this?.event?.status ?: -1,
    startDate = this?.event?.startDate ?: Date(),
    endDate = this?.event?.endDate ?: Date(),
    phone = this?.event?.phone ?: "",
    category = this?.event?.category ?: -1,
    isRead = false,
)

fun List<EventWitPhotos>.toEventEntityList() = map {
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

fun EventDto.toRoomDto() = EventsRoomDto(
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

fun List<EventDto>.toRoomEntityList() = map {
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

fun EventsDto.toEntity() = EventsEntity(
    events = this.eventDtos?.toEntityList() ?: arrayListOf()
)

fun List<EventDto>.toEntityList() = map {
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