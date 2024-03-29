package com.example.core.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id:Long,
    val image:Int,
    val title:String,
):Parcelable
