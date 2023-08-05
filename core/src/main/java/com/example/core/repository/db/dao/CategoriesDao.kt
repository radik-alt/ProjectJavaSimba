package com.example.core.repository.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.repository.db.dto.CategoryRoomDto
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao {

    @Query("SELECT * from Category")
    fun select(): Flow<List<CategoryRoomDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categoryRoomDto: CategoryRoomDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categoryRoomDto: List<CategoryRoomDto>)

    @Query("DELETE FROM Category")
    suspend fun delete()
}