package com.example.feature_events.di

import com.example.feature_events.data.repository.FilterRepository
import com.example.feature_events.data.repository.NewsRepository
import com.example.feature_events.data.repository_impl.FilterRepositoryImpl
import com.example.feature_events.data.repository_impl.NewsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface DataModule {
    @Binds
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository
    @Binds
    fun bindFilterRepository(impl: FilterRepositoryImpl): FilterRepository

}