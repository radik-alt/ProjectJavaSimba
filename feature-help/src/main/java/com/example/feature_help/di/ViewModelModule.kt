package com.example.feature_help.di

import android.app.Application
import com.example.feature_help.domain.usecase.HelpUseCase
import com.example.feature_help.presentation.viewmodel.HelpViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun provideHelpViewModel(
        application: Application,
        useCase: HelpUseCase
    ): HelpViewModel = HelpViewModel(application, useCase)
}