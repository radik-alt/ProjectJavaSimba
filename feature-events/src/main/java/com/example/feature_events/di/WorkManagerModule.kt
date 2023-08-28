package com.example.feature_events.di

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import com.example.core.repository.db.SimbaDataBase
import com.example.feature_events.service.DataWorkerFactory
import dagger.Module
import dagger.Provides

@Module
class WorkManagerModule {

    @Provides
    fun provideDonateWorkManager(
        application: Application,
        db: SimbaDataBase
    ): WorkManager {
        val configuration = Configuration.Builder()
            .setWorkerFactory(
                DataWorkerFactory(
                    db,
                    application
                )
            )
            .build()
        return WorkManager.getInstance(application).apply { 
            
        }
    }


}