package com.example.projectjavasimba.repository.db.utils

import androidx.room.TypeConverter
import java.util.Date

class DateConvertor {

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }
}