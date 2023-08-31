package com.example.feature_events.service

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDeepLinkBuilder
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.core.entity.EventEntity
import com.example.core.repository.db.SimbaDataBase
import com.example.feature_events.R
import com.example.feature_events.data.mapping.toEventEntity
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DonatWorkManager(
    private val context: Context,
    workerParameters: WorkerParameters,
    private val application: Application,
    private val db: SimbaDataBase
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val eventId = inputData.getInt(EVENT_ID, -1)
        val eventSum = inputData.getInt(EVENT_SUM, -1)
        val actionClick = inputData.getBoolean(ACTION_CLICK, false)

        Log.d("GetNotification", "StartWorkManager")

        try {
            if (eventId != -1) {
                db.eventsDao.selectById(eventId).let { event ->
                    if (event != null) {
                        Log.d("GetNotification", "StartNotification")
                        createChannelNotification()
                        showNotification(
                            event.event?.name ?: "",
                            eventSum,
                            event.toEventEntity(),
                            actionClick
                        )
                    } else {
                        return Result.failure()
                    }
                }
            } else {
                return Result.failure()
            }
        } catch (e: Exception) {
            return Result.failure()
        }
        return Result.success()
    }

    private fun showNotification(
        title: String,
        eventSum: Int,
        event: EventEntity,
        actionClick: Boolean
    ) {
        try {
            val pendingIntent = NavDeepLinkBuilder(context)
                .setGraph(R.navigation.events_nav)
                .setDestination(R.id.detail_fragment, Bundle().apply {
                    putParcelable("event", event)
                })
                .createPendingIntent()

            val receiverIntent = Intent().apply {
                action = LAST_NOTIFICATION
                putExtra(EVENT_ID, event.id)
                putExtra(EVENT_SUM, eventSum)
            }
            val receiverPendingIntent = PendingIntent.getBroadcast(
                application,
                0,
                receiverIntent,
                PendingIntent.FLAG_IMMUTABLE
            )

            Log.d("ReceiverNotification", receiverIntent.action.toString())

            val builder = if (actionClick) {
                NotificationCompat.Builder(application, CHANNEL_ID)
                    .setSmallIcon(com.example.core.R.drawable.ic_launcher_foreground)
                    .setContentTitle(title)
                    .setContentText(
                        context.getString(R.string.thanks_for_donate_notification_later),
                    )
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .build()
            } else {
                NotificationCompat.Builder(application, CHANNEL_ID)
                    .setSmallIcon(com.example.core.R.drawable.ic_launcher_foreground)
                    .setContentTitle(title)
                    .setContentText(
                        context.getString(R.string.thanks_for_donate, eventSum.toString()),
                    )
                    .addAction(
                        R.drawable.icon_calendar,
                        context.getString(R.string.remind_me_later),
                        receiverPendingIntent
                    )
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .build()
            }


            val manager = NotificationManagerCompat.from(application)
            manager.notify(NOTIFICATION_ID, builder)
        } catch (e: Exception) {
            Log.d("GetErrorMessage", e.message.toString())
        }
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
        } else {}
    }

    companion object {
        const val EVENT_ID = "EVENT_ID"
        const val EVENT_SUM = "EVENT_SUM"
        const val ACTION_CLICK = "CLICK_LAST_NOTIFICATION"
        const val NOTIFICATION_ID = 101

        const val WORK_NAME = "DONAT_WORKER"
        const val LAST_NOTIFICATION = "ACTION_LAST_NOTIFICATION"


        private const val CHANNEL_ID = "CHANNEL_ID"
        private const val CHANNEL_NAME = "Channel Name";
        private const val CHANNEL_DESC = "Channel Description";

        fun makeRequest(
            id: Int,
            sum: Int,
            delay: Long = 0,
            lastClick: Boolean = false
        ): OneTimeWorkRequest {
            val inputData = Data.Builder()
                .putInt(EVENT_ID, id)
                .putInt(EVENT_SUM, sum)
                .putBoolean(ACTION_CLICK, lastClick)
                .build()
            return OneTimeWorkRequestBuilder<DonatWorkManager>().apply {
                setInputData(inputData)
                setInitialDelay(delay, TimeUnit.MINUTES)
            }.build()
        }
    }

    class Factory @Inject constructor(
        private val application: Application,
        private val dataBase: SimbaDataBase
    ) : ChildWorkerFactory {

        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return DonatWorkManager(
                context,
                workerParameters,
                application,
                dataBase,
            )
        }
    }


}