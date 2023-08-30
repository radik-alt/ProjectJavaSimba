package com.example.feature_events.di

import com.example.feature_events.service.ChildWorkerFactory
import com.example.feature_events.service.DonatWorkManager
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(DonatWorkManager::class)
    fun bindRefreshDataWorkerFactory(worker: DonatWorkManager.Factory): ChildWorkerFactory
}
