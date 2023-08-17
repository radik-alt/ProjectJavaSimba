package com.example.projectjavasimba.di

import android.app.Application
import com.example.feature_help.di.HelpComponent
import com.example.feature_help.di.HelpComponentProvider

class SimbaApp : Application(), HelpComponentProvider {

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

}