package com.example.feature_help.di

import com.example.feature_help.presentation.view.HelpFragment
import dagger.Module
import dagger.Provides

@Module
object FeatureModule {

    @Provides
    fun provideHelpFragment() = HelpFragment()

}