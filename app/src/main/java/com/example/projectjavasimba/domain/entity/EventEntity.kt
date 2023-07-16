package com.example.projectjavasimba.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class EventEntity(
    val id: Int,
    val title: String,
    val description: String,
    val listImage: List<String>,
    val street: String,
    val phone: String,
    val category: Int,
    val status: Int,
    val createAt: Date,
    val startDate: Date,
    val endDate: Date,
    var isRead: Boolean
) : Parcelable
