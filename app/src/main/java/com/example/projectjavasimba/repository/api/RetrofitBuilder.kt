package com.example.projectjavasimba.repository.api

import android.util.Log
import com.example.projectjavasimba.repository.serializer.DateNetworkSerializer
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private const val BASE_URL = "https://97c46c90-4adc-41cd-a99a-5dd4a1c0ea7c.mock.pstmn.io"

    private val loggingInterceptor = HttpLoggingInterceptor { message ->
        Log.d(
            "OkHttpClient",
            message
        )
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateNetworkSerializer())
        .create()

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


    val apiService: PostmanApi = retrofit.create(PostmanApi::class.java)
}