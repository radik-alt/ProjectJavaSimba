package com.example.projectjavasimba.data

import android.content.Context
import org.json.JSONObject

class ParseJSON (
    private val context: Context,
) {

    fun parseEventJson() {
        val json = loadJsonFromAssets("news.json")
        val jsonObject = JSONObject(json)

    }

    fun parseCategoryJson() {
        val json = loadJsonFromAssets("category.json")
        val jsonObject = JSONObject(json)
    }

    private fun loadJsonFromAssets(fileName:String): String =
        context.assets.open(fileName).bufferedReader().use { it.readText() }
}