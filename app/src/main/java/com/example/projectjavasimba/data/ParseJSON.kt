package com.example.projectjavasimba.data

import android.content.Context
import android.util.Log
import com.example.projectjavasimba.data.entity.Category
import com.example.projectjavasimba.data.entity.Event
import com.google.gson.Gson

class ParseJSON (
    private val context: Context,
) {

    fun parseEventJson() : List<Event>{
        val gson = Gson()
        val listEvent = gson.fromJson(loadJsonFromAssets("news.json"), Array<Event>::class.java)
        return listEvent.toList()
    }

    fun parseCategoryJson(): List<Category> {
        val gson = Gson()
        val listCategory = gson.fromJson(loadJsonFromAssets("category.json"), Array<Category>::class.java)
        return listCategory.toList()
    }

    private fun loadJsonFromAssets(fileName:String): String =
        context.assets.open(fileName).bufferedReader().use { it.readText() }
}