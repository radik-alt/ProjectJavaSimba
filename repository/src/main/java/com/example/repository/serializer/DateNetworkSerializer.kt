package com.example.repository.serializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.util.Date

class DateNetworkSerializer : JsonDeserializer<Date> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date? {
        val timestamp = json?.asLong
        return if (timestamp != null) {
            Date(timestamp * 1000)
        } else {
            null
        }
    }
}