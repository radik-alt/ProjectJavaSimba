package com.example.feature_help.di

import android.app.Application
import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.example.feature_help.presentation.view.HelpFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton
import kotlin.properties.Delegates.notNull

@Singleton
@Component(
    modules = [
        DataBaseModule::class,
        DataModule::class,
        DomainModule::class,
        NetworkModule::class,
        ViewModelModule::class,
    ]
)
interface HelpComponent {

    fun inject(fragment: HelpFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): HelpComponent
    }
}

interface HelpDeps {}