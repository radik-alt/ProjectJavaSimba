package com.example.feature_events.service

import android.app.Application
import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.core.repository.api.PostmanApi
import com.example.core.repository.db.SimbaDataBase
import javax.inject.Inject

class DataWorkerFactory @Inject constructor(
    private val db: SimbaDataBase,
    private val context: Application
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return DonatWorkManager(
            appContext,
            workerParameters,
        )
    }
}