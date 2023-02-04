package com.example.projectjavasimba.data

import java.util.Date

data class Event(
    val id:Long,
    val title:String,
    val description:String,
    val listImage: List<String>,
    val date: Date,
    val street:String,
    val phone:String,
    val email:String,
)
