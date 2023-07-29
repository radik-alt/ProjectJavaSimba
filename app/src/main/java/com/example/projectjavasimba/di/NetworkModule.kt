package com.example.projectjavasimba.di

import com.example.projectjavasimba.repository.api.PostmanApi
import com.example.projectjavasimba.repository.api.RetrofitBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providePostmanApi(): PostmanApi {
        return RetrofitBuilder.apiService
    }

}