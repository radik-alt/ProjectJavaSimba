package com.example.projectjavasimba.data_impl.callable

import android.content.Context
import com.example.repository.dto.categories.CategoriesDto
import com.google.gson.Gson
import java.util.concurrent.Callable

class MyCallableCategory(
    private val context: Context,
) : Callable<com.example.repository.dto.categories.CategoriesDto> {

    override fun call(): com.example.repository.dto.categories.CategoriesDto {
        val gson = Gson()
        return gson.fromJson(loadJsonFromAssets("category.json"), com.example.repository.dto.categories.CategoriesDto::class.java)
    }

    private fun loadJsonFromAssets(fileName: String): String =
        context.assets.open(fileName).bufferedReader().use { it.readText() }
}