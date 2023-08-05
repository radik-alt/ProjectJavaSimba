package com.example.core.repository.api

import android.util.Log
import com.example.core.repository.serializer.DateNetworkSerializer
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date

object RetrofitBuilder {

    private const val BASE_URL = "https://cd9fa175-5397-4aea-adfc-05b749772360.mock.pstmn.io"

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
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()


    val apiService: PostmanApi = retrofit.create(PostmanApi::class.java)
}