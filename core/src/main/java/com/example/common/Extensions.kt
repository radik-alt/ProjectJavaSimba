package com.example.common

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
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

fun Activity.showBottomNavigation(bottomNavigationView: BottomNavigationView?) {
    if (bottomNavigationView != null && bottomNavigationView.visibility == View.GONE) {
        bottomNavigationView.show()
    }
}

fun Activity.hideBottomNavigation(bottomNavigationView: BottomNavigationView?) {
    if (bottomNavigationView != null && bottomNavigationView.visibility == View.VISIBLE) {
        bottomNavigationView.visibility = View.GONE
    }
}

inline fun <reified T : Serializable> Bundle.serializable(key: String): T? = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getSerializable(key) as? T
}
