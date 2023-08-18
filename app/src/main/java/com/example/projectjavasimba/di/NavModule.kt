package com.example.projectjavasimba.di

import android.app.Application
import com.example.projectjavasimba.module_navigation.AppRouter
import com.example.projectjavasimba.module_navigation.NavComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavModule {

    @Singleton
    @Provides
    fun provideNavRoute(
        context: Application
    ): AppRouter = NavComponent(context)

}