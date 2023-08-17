package com.example.projectjavasimba.di

import com.example.projectjavasimba.domain.usecase.FilterUseCase
import com.example.projectjavasimba.domain.usecase.HelpUseCase
import com.example.projectjavasimba.domain.usecase.NewsUseCase
import com.example.projectjavasimba.domain_impl.interactor.FilterInteractor
import com.example.projectjavasimba.domain_impl.interactor.HelpInteractor
import com.example.projectjavasimba.domain_impl.interactor.NewsInteractor
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindNewsUseCase(impl: NewsInteractor): NewsUseCase

    @Binds
    fun bindFilterUseCase(impl: FilterInteractor): FilterUseCase
}