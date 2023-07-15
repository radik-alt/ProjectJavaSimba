package com.example.projectjavasimba.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventEntity(
    val id:Int,
    val title:String,
    val description:String,
    val listImage: List<Int>,
    val date: String,
    val street:String,
    val phone:String,
    val email:String,
    val category: List<Category>,
    val listFriends: List<Int>,
    var isRead: Boolean
):Parcelable
