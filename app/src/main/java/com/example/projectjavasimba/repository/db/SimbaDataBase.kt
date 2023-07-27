package com.example.projectjavasimba.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projectjavasimba.repository.db.dao.CategoriesDao
import com.example.projectjavasimba.repository.db.dao.EventsDao
import com.example.projectjavasimba.repository.db.dto.CategoryRoomDto
import com.example.projectjavasimba.repository.db.dto.EventsRoomDto
import com.example.projectjavasimba.repository.db.dto.PhotoRoomDto

@Database(
    entities = [
        CategoryRoomDto::class,
        EventsRoomDto::class,
        PhotoRoomDto::class,
    ],
    version = 1
)
abstract class SimbaDataBase : RoomDatabase() {
    abstract fun categoriesDao(): CategoriesDao
    abstract fun eventsDao(): EventsDao


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
                    .build()

                INSTANCE = roomDataBaseInstance
                return roomDataBaseInstance
            }
        }

    }

}