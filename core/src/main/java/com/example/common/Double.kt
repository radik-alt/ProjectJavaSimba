package com.example.common

import java.util.*

fun Double.toRubleFormat(): String {
    return "${String.format(Locale.getDefault(), "%.2f", this)} $RUBLE"
}

fun Double.toString(accuracy: Int = -1): String {
    return if (accuracy >= 0)
        String.format(Locale.getDefault(), "%.${accuracy}f", this)
    else
        this.toString()
}