package com.example.projectjavasimba.di

import android.app.Application
import com.example.projectjavasimba.presentation.MainActivity
import com.example.projectjavasimba.presentation.newsFragment.view.NewsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    DataModule::class,
    DomainModule::class,
    DataBaseModule::class,
    ViewModelModule::class,
    NetworkModule::class
])
interface AppComponent {

    fun inject(app: SimbaApp)

    fun inject(mainActivity: MainActivity)

    fun inject(fragment: NewsFragment)

    @Component.Factory
    interface Factory{

        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }

}