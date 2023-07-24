package com.example.projectjavasimba.repository.api

import com.example.projectjavasimba.repository.dto.categories.CategoriesDto
import com.example.projectjavasimba.repository.dto.events.EventsDto
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface PostmanApi {

    @GET("categories")
    suspend fun getCategories(): CategoriesDto

    @GET("events")
    suspend fun getEvents(): EventsDto

}