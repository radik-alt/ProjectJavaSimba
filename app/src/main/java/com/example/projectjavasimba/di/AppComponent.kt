package com.example.projectjavasimba.di

import android.app.Application
import com.example.feature_events.di.EventModule
import com.example.feature_events.di.EventsComponent
import com.example.feature_help.di.HelpComponent
import com.example.main.presentation.MainActivity
import com.example.feature_events.presentation.filter_fragment.view.FilterFragment
import com.example.feature_events.presentation.newsFragment.view.NewsFragment
import com.example.feature_help.di.HelpModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        HelpModule::class,
        NetworkModule::class,
        DataBaseModule::class,
        EventModule::class
    ]
)
interface AppComponent {

    fun inject(app: SimbaApp)
    fun inject(mainActivity: MainActivity)

    val helpComponent: HelpComponent.Builder
    val eventComponent: EventsComponent.Builder

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}