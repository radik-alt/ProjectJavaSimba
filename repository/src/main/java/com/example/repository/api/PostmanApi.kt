package com.example.repository.api

import com.example.repository.dto.categories.CategoriesDto
import com.example.repository.dto.events.EventsDto
import retrofit2.http.GET

interface PostmanApi {

    @GET("categories")
    suspend fun getCategories(): CategoriesDto

    @GET("events")
    suspend fun getEvents(): EventsDto

}