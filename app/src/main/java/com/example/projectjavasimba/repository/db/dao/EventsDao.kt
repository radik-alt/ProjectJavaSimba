package com.example.projectjavasimba.repository.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.projectjavasimba.repository.db.dto.EventWitPhotos
import com.example.projectjavasimba.repository.db.dto.EventsRoomDto
import kotlinx.coroutines.flow.Flow

@Dao
interface EventsDao {

    @Transaction
    @Query("SELECT * FROM Events")
    fun select(): Flow<List<EventWitPhotos>>

    @Transaction
    @Insert(entity = EventsRoomDto::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: EventsRoomDto)

    @Transaction
    @Query("DELETE FROM Events")
    suspend fun delete()
}