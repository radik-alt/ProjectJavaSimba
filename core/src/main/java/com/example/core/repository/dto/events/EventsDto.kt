package com.example.core.repository.dto.events


import com.example.core.repository.dto.events.EventDto
import com.google.gson.annotations.SerializedName

data class EventsDto(
    @SerializedName("Events")
    val eventDtos: List<EventDto>?
)