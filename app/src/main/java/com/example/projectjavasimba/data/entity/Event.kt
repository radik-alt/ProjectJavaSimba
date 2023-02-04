package com.example.projectjavasimba.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Event(
    val id:Long,
    val title:String,
    val description:String,
    val listImage: List<String>,
    val date: Date,
    val street:String,
    val phone:String,
    val email:String,
    val category: Category
):Parcelable
