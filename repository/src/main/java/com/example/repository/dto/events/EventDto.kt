package com.example.repository.dto.events


import com.google.gson.annotations.SerializedName
import java.util.Date

data class EventDto(
    @SerializedName("address")
    val address: String?,
    @SerializedName("category")
    val category: Int?,
    @SerializedName("createAt")
    val createAt: Date?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("endDate")
    val endDate: Date?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("organisation")
    val organisation: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("photos")
    val photos: List<String>?,
    @SerializedName("startDate")
    val startDate: Date?,
    @SerializedName("status")
    val status: Int?
)