package com.example.feature_help.di

import android.app.Application
import com.example.feature_help.presentation.view.HelpFragment
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(
    modules = [
        DataModule::class,
        DomainModule::class,
        DataBaseModule::class,
        ViewModelModule::class,
        NetworkModule::class
    ],
)
interface HelpComponent {
    fun inject(fragment: HelpFragment)

    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): HelpComponent
    }
}