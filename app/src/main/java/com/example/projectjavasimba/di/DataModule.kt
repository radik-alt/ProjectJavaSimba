package com.example.projectjavasimba.di

import com.example.projectjavasimba.data.repository.MainRepository
import com.example.projectjavasimba.data.repository_impl.MainRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindMainRepository(impl: MainRepositoryImpl): MainRepository

}