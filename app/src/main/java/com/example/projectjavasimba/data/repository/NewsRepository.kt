package com.example.projectjavasimba.data.repository

import com.example.projectjavasimba.repository.dto.events.EventsDto
import io.reactivex.rxjava3.core.Observable

interface NewsRepository {

    suspend fun getEvents(): Observable<EventsDto>
}