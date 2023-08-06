package com.example.feature_help.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.core.entity.Category
import com.example.core.entity.EventEntity


class ServiceGetData: Service() {

    var callbackEventEntity: CallbackData<EventEntity> ?= null
    var callbackCategory : CallbackData<Category> ?= null

    fun getDataEvent() {
        // видоизменял получение данных с ParseJson()
//        val data = ParseJSON(applicationContext).parseEventJson().get()
//        callbackEvent?.onDataReceived(data)
    }

    fun getDataCategory() {
        // видоизменял получение данных с ParseJson()
//        val data = ParseJSON(applicationContext).parseCategoryJson().get()
//        callbackCategory?.onDataReceived(data)
    }

    override fun onBind(intent: Intent?): IBinder {
        return LocalBinder()
    }

    interface CallbackData<T> {
        fun onDataReceived(list: List<T>)
    }

    inner class LocalBinder : Binder() {
        fun getService(): ServiceGetData = this@ServiceGetData
    }
}