package com.example.feature_events.di

import android.app.Application
import com.example.feature_events.domain.usecase.FilterUseCase
import com.example.feature_events.domain.usecase.NewsUseCase
import com.example.feature_events.presentation.filter_news.viewmodel.FilterViewModel
import com.example.feature_events.presentation.news.viewmodel.NewsViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideNewsViewModel(
        application: Application,
        useCase: NewsUseCase
    ): NewsViewModel = NewsViewModel(application, useCase)

    @Provides
    fun provideFilterViewModel(
        application: Application,
        useCase: FilterUseCase
    ): FilterViewModel = FilterViewModel(application, useCase)
}