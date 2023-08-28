package com.example.feature_events.service

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.core.repository.db.SimbaDataBase
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DonatWorkManager(
    context: Context,
    workerParameters: WorkerParameters,
) : Worker(context, workerParameters) {

    @Inject
    lateinit var application: Application

    @Inject
    lateinit var db: SimbaDataBase

    override fun doWork(): Result {
        Log.d("GetWorker", "StartWorker")

        val eventId = inputData.getInt(EVENT_ID, -1)
        val eventSum = inputData.getInt(EVENT_SUM, -1)

        Log.d("GetWorker", eventId.toString())
        try {
            if (eventId != -1) {
                Log.d("GetWorker", "StartWorker")
                val response = db.eventsDao.selectById(eventId).let { event ->
                    if (event != null) {
                        Log.d("GetWorker", "StartWorker")
                        createChannelNotification()
                        showNotification(
                            event.event?.name ?: "",
                            "Спасибо, что пожертвовали $eventSum ₽!"
                        )
                    } else {
                        return Result.failure()
                    }
                }
                Log.d("GetWorker", "StartWorker: $response")
            } else {
                return Result.failure()
            }
        } catch (e: Exception) {
            Log.d("GetWorker", e.message.toString())
            return Result.failure()
        }
        return Result.success()
    }

    private fun showNotification(title: String, message: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Bh9qg9NivtU"))
        val pendingIntent = PendingIntent.getActivity(application, 0, intent, 0);

        val builder = NotificationCompat.Builder(application, CHANNEL_ID)
            .setSmallIcon(com.example.core.R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val manager = NotificationManagerCompat.from(application)
        manager.notify(NOTIFICATION_ID, builder)
    }

    private fun createChannelNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = CHANNEL_DESC
            val notificationManager = application.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        } else {
        }
    }

    companion object {
        const val EVENT_ID = "EVENT_ID"
        const val EVENT_NAME = "EVENT_NAME"
        const val EVENT_SUM = "EVENT_SUM"
        private const val NOTIFICATION_ID = 101

        const val WORK_NAME = "DONAT_WORKER"



        private const val CHANNEL_ID = "CHANNEL_ID"
        private const val CHANNEL_NAME = "Channel Name";
        private const val CHANNEL_DESC = "Channel Description";

        fun makeRequest(id: Int, nameEvent: String, sum: Int): OneTimeWorkRequest {
            val inputData = Data.Builder()
                .putInt(EVENT_ID, id)
                .putString(EVENT_NAME, nameEvent)
                .putInt(EVENT_SUM, sum)
                .build()
            return OneTimeWorkRequestBuilder<DonatWorkManager>().apply {
                setInputData(inputData)
//                setConstraints(makeConstrains())
            }.build()
        }

        private fun makeConstrains(): Constraints {
            return Constraints.Builder()
//                .setRequiresCharging(true)
                .build()
        }
    }
}