package com.example.common

import java.util.*

fun Long.toDate(): Date {
    return Calendar.getInstance().apply {
        timeInMillis = this@toDate
    }.time
}