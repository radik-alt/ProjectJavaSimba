package com.example.projectjavasimba.data_impl

import android.util.Log
import com.example.projectjavasimba.data.repository.NewsRepository
import com.example.projectjavasimba.repository.api.RetrofitBuilder
import com.example.projectjavasimba.repository.dto.events.EventsDto
import io.reactivex.rxjava3.core.Observable

class NewsRepositoryImpl: NewsRepository {

    private val api = RetrofitBuilder.apiService

    override fun getEvents(): Observable<EventsDto> {
        val request = api.getEvents()
        Log.d("GetInfo", request.toString())
        return request
    }

}