package com.example.projectjavasimba.di

import android.app.Application
import com.example.main.presentation.MainActivity
import com.example.projectjavasimba.presentation.filterFragment.view.FilterFragment
import com.example.projectjavasimba.presentation.helpFragment.view.HelpFragment
import com.example.projectjavasimba.presentation.newsFragment.view.NewsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        DataBaseModule::class,
        ViewModelModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    fun inject(app: SimbaApp)
    fun inject(mainActivity: MainActivity)
    fun inject(fragment: NewsFragment)
    fun inject(fragment: HelpFragment)
    fun inject(fragment: FilterFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }

}