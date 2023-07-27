package com.example.projectjavasimba.repository.db.dto

import androidx.room.Embedded
import androidx.room.Relation


data class EventWitPhotos(
    @Embedded val event: EventsRoomDto?,
    @Relation(parentColumn = "id", entityColumn = "eventId")
    val eventPhotos: List<PhotoRoomDto>?
)
