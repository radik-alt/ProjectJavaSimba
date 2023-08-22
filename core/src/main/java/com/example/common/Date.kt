package com.example.common

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

const val yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss.SSS"
const val yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss"
const val yyyy_MM_dd = "yyyy-MM-dd"
const val yyyy_MM_01 = "yyyy-MM-01"
const val dd_MM_yyyy_HH_mm_ss = "dd.MM.yyyy HH:mm:ss"
const val dd_MM_yyyy2 = "dd-MM-yyyy"
const val dd_MM_yyyy = "dd.MM.yyyy"
const val MM_dd_yyyy = "MM-dd-yyyy"
const val dd_MM_yyyy_hh_mm = "dd.MM.yyyy hh:mm"
const val dd_MM_yyyy_hh_mm_2 = "dd.MM.yyyy в HH:mm"
const val dd_MM_yyyy_HH_mm = "dd.MM.yyyy, HH:mm"
const val dd_MMMM_yyyy = "dd MMMM yyyy"
const val dd_MMMM_yyyy_hh_mm = "dd MMMM yyyy, HH:mm"
const val MMMM_yyyy = "MMMM yyyy"
const val MMMM = "MMMM"
const val MMM = "MMM"
const val MMM_yyyy = "MMM yyyy"
const val LLLL_yyyy = "LLLL yyyy"
const val yyyy = "yyyy"
const val HH_mm = "HH:mm"
const val hh_mm = "hh:mm"

fun Date?.toString(format: String, locale: Locale = Locale.getDefault()): String = if (this != null)
    SimpleDateFormat(format, locale).format(this)
else
    ""


fun Date?.toStringOrDefault(
    format: String,
    defStr: String,
    locale: Locale = Locale.getDefault()
): String = if (this != null)
    SimpleDateFormat(format, locale).format(this)
else
    defStr

fun Date?.toNetFormat(): String = this.toString(yyyy_MM_dd_HH_mm_ss_SSS).replace(" ", "T")

fun Date.copy(): Date {
    return this.clone() as Date
}

fun Date.toCalendar(): Calendar = Calendar.getInstance().apply {
    time = this@toCalendar
}