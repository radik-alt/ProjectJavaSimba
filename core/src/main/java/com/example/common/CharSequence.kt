package com.example.common

fun CharSequence?.toDouble(): Double = this.toString().toDoubleOrNull() ?: 0.0

fun CharSequence?.toIntOrDefault(def: Int) = try {
    this?.toString()?.toInt() ?: 0
} catch (t: Throwable) {
    def
}

fun CharSequence?.toFloatOrDefault(def: Float) = try {
    this?.toString()?.replace(",", ".")?.toFloat() ?: 0.0f
} catch (t: Throwable) {
    def
}

fun CharSequence?.toDoubleOrDefault(def: Double) = try {
    this?.toString()?.replace(",", ".")?.toDouble() ?: 0.0
} catch (t: Throwable) {
    def
}