package com.example.projectjavasimba.repository.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.projectjavasimba.repository.db.dto.CategoryRoomDto
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao {

    @Query("SELECT * from Category")
    fun select(): Flow<List<CategoryRoomDto>>

    @Insert(entity = CategoryRoomDto::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categoryRoomDto: CategoryRoomDto)

    @Query("DELETE FROM Category")
    suspend fun delete()
}