package com.example.projectjavasimba.di

import android.app.Application
import androidx.room.Room
import com.example.repository.db.SimbaDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataBaseModule {

    @Provides
    @Singleton
    fun provideSimbaDatabase(context: Application): SimbaDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            SimbaDataBase::class.java,
            "SimbaDataBase"
        )
            .fallbackToDestructiveMigration()
            .build()

    }

}