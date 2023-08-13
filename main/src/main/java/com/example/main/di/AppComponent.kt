package com.example.main.di

import android.app.Application
import com.example.feature_help.di.FeatureModule
import com.example.feature_help.di.HelpComponent
import com.example.main.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [FeatureModule::class],
)
interface AppComponent {

    fun inject(app: SimbaApp)
    fun inject(mainActivity: MainActivity)
    fun plusHelpFeature(): HelpComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}