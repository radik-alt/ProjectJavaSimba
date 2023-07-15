package com.example.projectjavasimba.repository.dto.categories


import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("name_en")
    val nameEnDto: NameEnDto?
)