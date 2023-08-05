package com.example.core.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.core.repository.db.dao.CategoriesDao
import com.example.core.repository.db.dao.EventsDao
import com.example.core.repository.db.dto.CategoryRoomDto
import com.example.core.repository.db.dto.EventsRoomDto
import com.example.core.repository.db.dto.PhotoRoomDto

@Database(
    entities = [
        CategoryRoomDto::class,
        EventsRoomDto::class,
        PhotoRoomDto::class,
    ],
    version = 3,
    exportSchema = false
)
abstract class SimbaDataBase : RoomDatabase() {
    abstract val categoriesDao: CategoriesDao
    abstract val eventsDao: EventsDao


    companion object {
        @Volatile
        var INSTANCE: SimbaDataBase? = null

        fun getDatabaseNotes(context: Context): SimbaDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val roomDataBaseInstance = Room.databaseBuilder(
                    context,
                    SimbaDataBase::class.java,
                    "SimbaDataBase"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = roomDataBaseInstance
                return roomDataBaseInstance
            }
        }

    }

}