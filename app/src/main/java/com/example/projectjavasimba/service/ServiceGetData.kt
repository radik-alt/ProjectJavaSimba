package com.example.projectjavasimba.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.projectjavasimba.data.entity.Category
import com.example.projectjavasimba.data.entity.Event


class ServiceGetData: Service() {

    var callbackEvent: CallbackData<Event> ?= null
    var callbackCategory : CallbackData<Category> ?= null

    fun getDataEvent() {
//        val data = ParseJSON(applicationContext).parseEventJson().get()
//        callbackEvent?.onDataReceived(data)
    }

    fun getDataCategory() {
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