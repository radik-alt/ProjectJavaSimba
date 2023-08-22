package com.example.common

import android.content.res.Resources
import java.util.Locale

fun Float.dpToPx() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Float.toString(accuracy: Int = -1): String {
    return if (accuracy >= 0)
        String.format(Locale.getDefault(), "%.${accuracy}f", this)
    else
        this.toString()
}