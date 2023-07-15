package com.example.projectjavasimba.repository.dto.categories


import com.google.gson.annotations.SerializedName

data class NameEnDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("name_en")
    val nameEn: String?
)