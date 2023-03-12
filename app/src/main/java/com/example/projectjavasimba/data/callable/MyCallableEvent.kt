package com.example.projectjavasimba.data.callable

import android.content.Context
import com.example.projectjavasimba.data.entity.Event
import com.google.gson.Gson
import java.util.concurrent.Callable

class MyCallableEvent(
    private val context: Context,
) : Callable<List<Event>> {
    override fun call(): List<Event> {
        val gson = Gson()
        val listEvent = gson.fromJson(loadJsonFromAssets("news.json"), Array<Event>::class.java)
        return listEvent.toList()
    }

    private fun loadJsonFromAssets(fileName: String): String =
        context.assets.open(fileName).bufferedReader().use { it.readText() }
}