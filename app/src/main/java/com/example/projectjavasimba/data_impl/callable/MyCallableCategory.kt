package com.example.projectjavasimba.data_impl.callable

import android.content.Context
import com.example.core.repository.dto.categories.CategoriesDto
import com.google.gson.Gson
import java.util.concurrent.Callable

class MyCallableCategory(
    private val context: Context,
) : Callable<CategoriesDto> {

    override fun call(): CategoriesDto {
        val gson = Gson()
        return gson.fromJson(loadJsonFromAssets("category.json"), CategoriesDto::class.java)
    }

    private fun loadJsonFromAssets(fileName: String): String =
        context.assets.open(fileName).bufferedReader().use { it.readText() }
}