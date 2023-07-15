package com.example.projectjavasimba.repository.api

import com.example.projectjavasimba.repository.dto.categories.CategoryDto
import com.example.projectjavasimba.repository.dto.events.EventsDto
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.POST

interface PostmanApi {

    @GET("categories")
    suspend fun getCategories(): CategoryDto

    @GET("events")
    fun getEvents(): Observable<EventsDto>

}