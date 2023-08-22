package com.example.common

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

val Context.appVersion: String
    get() = try {
        packageManager.getPackageInfo(packageName, 0).versionName
    } catch (e: Exception) {
        ""
    }

fun Context.getNotificationManager(channelId: String, channelName: String): NotificationManager =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        getSystemService(NotificationManager::class.java).apply {
            createNotificationChannel(
                NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
        }
    } else {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }