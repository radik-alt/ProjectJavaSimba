package com.example.projectjavasimba.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.projectjavasimba.data.entity.Category
import com.example.projectjavasimba.data.entity.Event
import com.google.gson.Gson
import java.lang.Thread.sleep
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import kotlin.concurrent.thread

class ParseJSON(
    private val context: Context,
) {

    fun parseEventJson(): Future<List<Event>> {
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        val future = executor.submit(MyCallable(context))
        executor.shutdown()
        return future
    }

    fun parseCategoryJson(): List<Category> {
        val gson = Gson()
        val listCategory =
            gson.fromJson(loadJsonFromAssets("category.json"), Array<Category>::class.java)
        return listCategory.toList()
    }

    private fun loadJsonFromAssets(fileName: String): String =
        context.assets.open(fileName).bufferedReader().use { it.readText() }
}