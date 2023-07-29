package com.example.projectjavasimba.di

import com.example.projectjavasimba.domain.usecase.NewsUseCase
import com.example.projectjavasimba.domain_impl.interactor.NewsInteractor
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindNewsUseCase(impl: NewsInteractor): NewsUseCase

}