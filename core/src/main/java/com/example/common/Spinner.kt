package com.example.common

import android.widget.Spinner

fun <T> Spinner.getTypedSelectedItem(): T = selectedItem as T
