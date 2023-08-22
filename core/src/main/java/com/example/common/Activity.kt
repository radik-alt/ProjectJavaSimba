package com.example.common

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideSoftKeyboard() {
    (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).run {
        hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}

fun Activity.hideSoftKeyboard(view: View) {
    (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).run {
        hideSoftInputFromWindow(view.windowToken, 0)
    }
}