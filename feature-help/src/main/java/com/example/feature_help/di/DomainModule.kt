package com.example.feature_help.di

import com.example.feature_help.domain.interactor.HelpInteractor
import com.example.feature_help.domain.usecase.HelpUseCase
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindHelpUseCase(impl: HelpInteractor): HelpUseCase

}