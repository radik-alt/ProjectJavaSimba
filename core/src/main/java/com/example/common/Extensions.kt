package com.example.common

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.Serializable


fun View.showSoftKeyboard() =
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)

fun View.hideSoftKeyboard() =
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(windowToken, 0)

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide(visible: Int = View.GONE) {
    visibility = visible
}

fun View.disable() {
    isEnabled = false
}

fun View.enable() {
    isEnabled = true
}

val View.isShown: Boolean
    get() = visibility == View.VISIBLE

val View.isHidden: Boolean
    get() = visibility == View.GONE || visibility == View.INVISIBLE

fun <T> View?.geTypedTag(): T? = if (this != null) tag as T else null

fun View.setBottomPadding(newPadding: Int) {
    this.setPadding(this.paddingLeft, this.paddingTop, this.paddingRight, newPadding)
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
