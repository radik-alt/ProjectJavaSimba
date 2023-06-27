package com.example.projectjavasimba.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.example.projectjavasimba.data.ParseJSON
import com.example.projectjavasimba.data.entity.Event


class ServiceGetData: Service() {

    var callback: CallbackData ?= null

    fun getData() {
//        val data = ParseJSON(applicationContext).parseEventJson().get()
//        callback?.onDataReceived(data)
    }

    override fun onBind(intent: Intent?): IBinder {
        return LocalBinder()
    }

    interface CallbackData {
        fun onDataReceived(list: List<Event>)
    }

    inner class LocalBinder : Binder() {
        fun getService(): ServiceGetData = this@ServiceGetData
    }
}