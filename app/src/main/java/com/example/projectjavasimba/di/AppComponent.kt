package com.example.projectjavasimba.di

import android.app.Application
import com.example.feature_help.di.HelpComponent
import com.example.feature_help.presentation.view.HelpFragment
import com.example.main.presentation.MainActivity
import com.example.projectjavasimba.presentation.filterFragment.view.FilterFragment
import com.example.projectjavasimba.presentation.newsFragment.view.NewsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        HelpModule::class,
        NetworkModule::class,
        DataBaseModule::class,
        ViewModelModule::class,
        DomainModule::class,
        DataModule::class
    ]
)
interface AppComponent {

    fun inject(app: SimbaApp)
    fun inject(mainActivity: MainActivity)
    fun inject(fragment: NewsFragment)
    fun inject(fragment: FilterFragment)

    val helpComponent: HelpComponent.Builder

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}