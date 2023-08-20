package com.example.projectjavasimba.di

import android.app.Application
import com.example.projectjavasimba.domain.usecase.MainUseCase
import com.example.projectjavasimba.presentation.auth.viewmodel.AuthViewModel
import com.example.projectjavasimba.presentation.main.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModel {

    @Provides
    fun provideMainViewModel(
        application: Application,
        mainUseCase: MainUseCase
    ): MainViewModel = MainViewModel(application, mainUseCase)

    @Provides
    fun provideAuthViewModel(): AuthViewModel = AuthViewModel()

}