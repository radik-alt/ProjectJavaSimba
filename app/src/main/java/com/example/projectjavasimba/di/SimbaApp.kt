package com.example.projectjavasimba.di

import android.app.Application
import androidx.work.Configuration
import com.example.core.repository.db.SimbaDataBase
import com.example.feature_events.di.EventComponentProvider
import com.example.feature_events.di.EventsComponent
import com.example.feature_events.service.DonateWorkFactory
import com.example.feature_events.service.WorkerFactory
import com.example.feature_help.di.HelpComponent
import com.example.feature_help.di.HelpComponentProvider
import javax.inject.Inject

class SimbaApp : Application(), HelpComponentProvider, EventComponentProvider,
    Configuration.Provider {

//    @Inject
//    lateinit var workerFactory: WorkerFactory

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

    @Inject
    lateinit var db: SimbaDataBase

    @Inject
    lateinit var application: Application

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    override fun provideHelpComponent(): HelpComponent {
        return component.helpComponent.create()
    }

    override fun provideEventComponent(): EventsComponent {
        return component.eventComponent.build()
    }

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setWorkerFactory(
                DonateWorkFactory(
                    application, db
                )
            )
            .build()

}