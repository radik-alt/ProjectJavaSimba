package com.example.common

import android.os.Build
import android.os.Parcel
import java.math.BigDecimal
import java.util.*

fun Parcel.readDate(): Date? {
    val serializable = this.readSerializable()
    return if (serializable != null) serializable as Date else null
}

fun Parcel.readBigDecimal(defVal: Double = 0.0) =
    readSerializable(BigDecimal::class.java) ?: BigDecimal(defVal)

fun <T> Parcel.readSerializable(clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.readSerializable(clazz.classLoader, clazz)
    } else {
        try {
            this.readSerializable() as T
        } catch (_: Throwable) {
            null
        }
    }
}