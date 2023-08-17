package com.example.projectjavasimba.di

import com.example.projectjavasimba.data.repository.FilterRepository
import com.example.projectjavasimba.data.repository.HelpRepository
import com.example.projectjavasimba.data.repository.NewsRepository
import com.example.projectjavasimba.data_impl.FilterRepositoryImpl
import com.example.projectjavasimba.data_impl.HelpRepositoryImpl
import com.example.projectjavasimba.data_impl.NewsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository
    @Binds
    fun bindFilterRepository(impl: FilterRepositoryImpl): FilterRepository

}