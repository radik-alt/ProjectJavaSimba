package com.example.projectjavasimba.domain.usecase

import com.example.projectjavasimba.repository.dto.events.EventsDto
import io.reactivex.rxjava3.core.Observable

interface NewsUseCase {

    suspend fun getEvents(): Observable<EventsDto>
}