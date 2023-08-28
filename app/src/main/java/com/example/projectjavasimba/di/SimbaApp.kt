package com.example.projectjavasimba.di

import android.app.Application
import com.example.feature_events.di.EventComponentProvider
import com.example.feature_events.di.EventsComponent
import com.example.feature_events.service.DataWorkerFactory
import com.example.feature_help.di.HelpComponent
import com.example.feature_help.di.HelpComponentProvider
import javax.inject.Inject

class SimbaApp : Application(), HelpComponentProvider, EventComponentProvider {

    @Inject
    lateinit var workerFactory: DataWorkerFactory

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

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

}