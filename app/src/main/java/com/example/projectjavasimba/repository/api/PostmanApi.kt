package com.example.projectjavasimba.repository.api

import com.example.projectjavasimba.repository.dto.categories.CategoriesDto
import com.example.projectjavasimba.repository.dto.events.EventsDto
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface PostmanApi {

    @GET("categories")
    fun getCategories(): Observable<CategoriesDto>

    @GET("events")
    fun getEvents(): Observable<EventsDto>

}