package com.example.projectjavasimba.common.utils

import android.os.Build
import android.os.Bundle
import android.view.View
import java.io.Serializable


fun View.hide(gone:Boolean = false) {
    visibility = if (gone) {
        View.GONE
    } else {
        View.INVISIBLE
    }
}

fun View.show() {
    visibility = View.VISIBLE
}

inline fun <reified T : Serializable> Bundle.serializable(key: String): T? = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getSerializable(key) as? T
}
