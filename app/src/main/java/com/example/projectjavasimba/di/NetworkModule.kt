package com.example.projectjavasimba.di

import com.example.core.repository.api.PostmanApi
import com.example.core.repository.api.RetrofitBuilder
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