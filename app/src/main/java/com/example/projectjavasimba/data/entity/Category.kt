package com.example.projectjavasimba.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id:Long,
    val image:String,
    val title:String,
    val enable:Boolean
):Parcelable
