package com.example.feature_events.di

import com.example.feature_events.domain.interactor.FilterInteractor
import com.example.feature_events.domain.interactor.NewsInteractor
import com.example.feature_events.domain.usecase.FilterUseCase
import com.example.feature_events.domain.usecase.NewsUseCase
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {
    @Binds
    fun bindNewsUseCase(impl: NewsInteractor): NewsUseCase
    @Binds
    fun bindFilterUseCase(impl: FilterInteractor): FilterUseCase
}