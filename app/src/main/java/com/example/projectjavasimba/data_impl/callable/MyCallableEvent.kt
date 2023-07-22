package com.example.projectjavasimba.data_impl.callable

import android.content.Context
import com.example.projectjavasimba.domain.entity.EventEntity
import com.google.gson.Gson
import java.util.concurrent.Callable

class MyCallableEvent(
    private val context: Context,
) : Callable<List<EventEntity>> {
    override fun call(): List<EventEntity> {
        val gson = Gson()
        val listEventEntity = gson.fromJson(loadJsonFromAssets("news.json"), Array<EventEntity>::class.java)
        return listEventEntity.toList()
    }

    private fun loadJsonFromAssets(fileName: String): String =
        context.assets.open(fileName).bufferedReader().use { it.readText() }
}