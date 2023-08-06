package com.example.feature_help.di

import com.example.feature_help.data.repository.HelpRepository
import com.example.feature_help.data.repositoryImpl.HelpRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindHelpRepository(impl: HelpRepositoryImpl): HelpRepository
}