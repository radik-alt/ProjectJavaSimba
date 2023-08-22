package com.example.common

import android.util.Base64
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val RUBLE = " \u20BD"
const val dash = "—"


fun String.toBase64(flags: Int = Base64.NO_WRAP): String {
    return if (this.isNotEmpty()) {
        Base64.encodeToString(this.encodeToByteArray(), flags)
    } else {
        this
    }
}

fun String.fromBase64(flags: Int = Base64.NO_WRAP): ByteArray {
    return if (this.isNotEmpty()) {
        Base64.decode(this, flags)
    } else {
        ByteArray(0)
    }
}

fun String?.capitalize(): String {
    if (this.isNullOrEmpty()) {
        return ""
    }
    val first = this[0]
    return if (this.length >= 2) {
        first.uppercaseChar().toString() + this.substring(1).lowercase()
    } else {
        first.uppercaseChar().toString()
    }
}

fun String?.capitalize(char: Char): String {
    return if (!this.isNullOrBlank()) {
        val strings = this.split(char)
        val stringBuilder = StringBuilder()
        strings.forEach {
            stringBuilder.append(it.capitalize())
            stringBuilder.append(char)
        }
        stringBuilder.trim().toString()
    } else {
        return ""
    }
}

fun <T : Enum<T>> String?.toEnum(cls: Class<T>, default: T): T {
    return try {
        if (!this.isNullOrEmpty()) {
            java.lang.Enum.valueOf(cls, this)
        } else {
            default
        }
    } catch (t: Throwable) {
        default
    }
}

fun String?.toDate(format: String): Date? {
    return if (!this.isNullOrBlank()) {
        SimpleDateFormat(format, Locale.getDefault()).parse(this.replace('T', ' '))
    } else {
        null
    }
}

val String?.isNumeric: Boolean
    get() = if (this != null) {
        this.toBigDecimalOrNull() != null
    } else {
        false
    }

fun String?.toBigDecimalOrDefault(def: Double): BigDecimal {
    return this?.replace(",", ".")?.toBigDecimalOrNull() ?: BigDecimal(def)
}

fun String?.toBooleanOrDefault(def: Boolean) = this?.toBooleanStrictOrNull() ?: def

fun String?.applyPhoneMask(): String {
    if (!this.isNullOrBlank()) {
        if (!this.containsPhoneMask()) {
            val strBld = StringBuilder()
            var ph = this
            if (ph.length == 10)
                ph = "7$ph"
            ph.replace("+", "", true).forEachIndexed { i, char ->
                if (i == 0) {
                    if (char == '8') {
                        strBld.append("+7 (")
                    } else {
                        strBld.append("+").append(char).append(" (")
                    }
                } else {
                    when (i) {
                        3 -> strBld.append(char).append(") ")
                        6 -> strBld.append(char).append(" - ")
                        8 -> strBld.append(char).append(" - ")
                        else -> strBld.append(char)
                    }
                }
            }
            return strBld.toString()
        } else {
            return this
        }
    } else {
        return ""
    }
}

fun String?.containsPhoneMask(): Boolean {
    return this?.contains("-") == true
}