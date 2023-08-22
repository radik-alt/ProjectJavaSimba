package com.example.common

import android.widget.AutoCompleteTextView

fun <T> AutoCompleteTextView.getTypedAdapter() = this.adapter as T