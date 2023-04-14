package com.example.projectjavasimba.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val id:Long,
    val title:String,
    val description:String,
    val listImage: List<Int>,
    val date: String,
    val street:String,
    val phone:String,
    val email:String,
    val category: List<Category>,
    val listFriends: List<Int>,
    val isRead: Boolean
):Parcelable
