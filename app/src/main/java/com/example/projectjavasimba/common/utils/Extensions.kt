package com.example.projectjavasimba.common.utils

import android.view.View


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

