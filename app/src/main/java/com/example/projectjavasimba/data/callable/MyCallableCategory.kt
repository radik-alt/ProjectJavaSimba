package com.example.projectjavasimba.data.callable

import android.content.Context
import com.example.projectjavasimba.data.entity.Category
import com.google.gson.Gson
import java.util.concurrent.Callable

class MyCallableCategory(
    private val context: Context,
) : Callable<List<Category>> {

    override fun call(): List<Category> {
        val gson = Gson()
        val listEvent = gson.fromJson(loadJsonFromAssets("category.json"), Array<Category>::class.java)
        return listEvent.toList()
    }

    private fun loadJsonFromAssets(fileName: String): String =
        context.assets.open(fileName).bufferedReader().use { it.readText() }
}