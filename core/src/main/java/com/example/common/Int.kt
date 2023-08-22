package com.example.common

import android.content.res.Resources
import kotlin.math.abs
import kotlin.math.log10

fun Int.dpToPx() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.length() = when (this) {
    0 -> 1
    else -> log10(abs(toDouble())).toInt() + 1
}