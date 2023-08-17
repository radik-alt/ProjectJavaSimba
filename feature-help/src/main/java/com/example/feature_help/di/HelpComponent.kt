package com.example.feature_help.di

import com.example.feature_help.presentation.view.HelpFragment
import dagger.Subcomponent


@Subcomponent(
    modules = [
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class,
    ]
)
interface HelpComponent {

    fun inject(fragment: HelpFragment)

    @Subcomponent.Builder
    interface Builder {
        fun create(): HelpComponent
    }
}

