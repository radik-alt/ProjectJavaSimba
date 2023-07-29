package com.example.projectjavasimba.di

import android.app.Application
import com.example.projectjavasimba.domain.usecase.NewsUseCase
import com.example.projectjavasimba.presentation.newsFragment.viewmodel.NewsViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideNewsViewModel(
        application: Application,
        useCase: NewsUseCase
    ): NewsViewModel = NewsViewModel(application, useCase)
}