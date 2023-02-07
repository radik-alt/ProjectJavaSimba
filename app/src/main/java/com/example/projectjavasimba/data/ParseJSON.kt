package com.example.projectjavasimba.data

import android.content.Context
import android.util.Log
import com.example.projectjavasimba.data.entity.CategoryListEntity
import com.example.projectjavasimba.data.entity.Event
import com.example.projectjavasimba.data.entity.EventListEntity
import com.google.gson.Gson

class ParseJSON (
    private val context: Context,
) {

    fun parseEventJson() {
        val event = Gson().fromJson<Event>(loadJsonFromAssets("news.json"), EventListEntity::class.java)
        Log.d("GetJsonConvert", event.toString())
    }

    fun parseCategoryJson() {
        val category = Gson().fromJson(loadJsonFromAssets("category.json"), CategoryListEntity::class.java)
        Log.d("GetJsonConvert", category.toString())
    }

    private fun loadJsonFromAssets(fileName:String): String =
        context.assets.open(fileName).bufferedReader().use { it.readText() }
}