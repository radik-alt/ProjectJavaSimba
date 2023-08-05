package com.example.core.repository.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.TypeConverters
import com.example.core.repository.db.dto.EventWitPhotos
import com.example.core.repository.db.dto.EventsRoomDto
import com.example.core.repository.db.dto.PhotoRoomDto
import com.example.core.repository.db.utils.DateConvertor
import kotlinx.coroutines.flow.Flow

@Dao
@TypeConverters(DateConvertor::class)
interface EventsDao {

    @Transaction
    @Query("SELECT * FROM Events")
    fun select(): Flow<List<EventWitPhotos>>

    @Insert(entity = EventsRoomDto::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: EventsRoomDto): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(events: List<EventsRoomDto>)

    @Insert(entity = PhotoRoomDto::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(event: PhotoRoomDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(event: List<PhotoRoomDto>)

    @Transaction
    @Query("DELETE FROM Events")
    suspend fun delete()
}