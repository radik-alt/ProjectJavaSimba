package com.example.projectjavasimba.di

import com.example.feature_help.di.HelpComponent
import dagger.Module

@Module(
    subcomponents = [
        HelpComponent::class
    ]
)
class HelpModule