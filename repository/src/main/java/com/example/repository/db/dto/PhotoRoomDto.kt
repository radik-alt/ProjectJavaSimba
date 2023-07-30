package com.example.repository.db.dto

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Photos",
)
data class PhotoRoomDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val eventId: Int,
    val photoUrl: String
)
