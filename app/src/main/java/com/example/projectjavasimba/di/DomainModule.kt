package com.example.projectjavasimba.di

import com.example.projectjavasimba.domain.interactor.MainInteractor
import com.example.projectjavasimba.domain.usecase.MainUseCase
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindMainUseCase(impl: MainInteractor): MainUseCase

}