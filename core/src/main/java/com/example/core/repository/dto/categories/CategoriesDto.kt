package com.example.core.repository.dto.categories


import com.google.gson.annotations.SerializedName

data class CategoriesDto(
    @SerializedName("Categories")
    val categories: List<CategoryDto>?
)