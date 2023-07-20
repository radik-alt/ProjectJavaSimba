package com.example.projectjavasimba.domain.usecase

import android.content.Context
import com.example.projectjavasimba.domain.entity.EventsEntity
import com.example.projectjavasimba.repository.dto.events.EventsDto
import io.reactivex.rxjava3.core.Observable

interface NewsUseCase {
    fun getEvents(context: Context): Observable<EventsEntity>
}