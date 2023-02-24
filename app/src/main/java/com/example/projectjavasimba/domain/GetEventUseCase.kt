package com.example.projectjavasimba.domain

import com.example.projectjavasimba.data.entity.Event
import com.example.projectjavasimba.utils.DefaultData

class GetEventUseCase {

    fun getEventUseCase(): List<Event> {
        return listOf(
           DefaultData.defaultEvent
        )
    }

}