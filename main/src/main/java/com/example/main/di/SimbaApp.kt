package com.example.main.di

import android.app.Application

class SimbaApp : Application() {

//    val component by lazy {
//        DaggerAppComponent.factory().create(this)
//    }

    override fun onCreate() {
        super.onCreate()
//        component.inject(this)
    }

}