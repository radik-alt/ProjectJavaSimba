package com.example.projectjavasimba.di

import com.example.projectjavasimba.data.repository.NewsRepository
import com.example.projectjavasimba.data_impl.NewsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository

}