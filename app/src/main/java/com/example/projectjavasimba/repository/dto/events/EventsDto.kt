package com.example.projectjavasimba.repository.dto.events


import com.google.gson.annotations.SerializedName

data class EventsDto(
    @SerializedName("Events")
    val eventDtos: List<EventDto>?
)