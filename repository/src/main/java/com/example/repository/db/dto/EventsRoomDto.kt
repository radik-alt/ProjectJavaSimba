package com.example.repository.db.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverters
import com.example.repository.db.utils.DateConvertor
import java.util.Date

@TypeConverters(DateConvertor::class)
@Entity(tableName = "Events")
data class EventsRoomDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val address: String,
    val category: Int,
    val createAt: Date,
    val description: String,
    val endDate: Date,
    val name: String,
    val organisation: String,
    val phone: String,
    val startDate: Date,
    val status: Int
)
