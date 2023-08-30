package com.example.feature_events.service

import android.app.Application
import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.core.repository.db.SimbaDataBase

class DonateWorkFactory(
    private val application: Application,
    private val db: SimbaDataBase
): WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return DonatWorkManager(
            appContext,
            workerParameters,
            application,
            db
        )
    }
}