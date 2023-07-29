package com.example.projectjavasimba.di

import com.example.projectjavasimba.repository.api.PostmanApi
import com.example.projectjavasimba.repository.api.RetrofitBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): RetrofitBuilder {
        return RetrofitBuilder
    }

    @Provides
    @Singleton
    fun providePostmanApi(): PostmanApi {
        return RetrofitBuilder.apiService
    }

}