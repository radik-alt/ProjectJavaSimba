package com.example.common

import java.util.*

val Calendar.hour: Int get() = this.get(Calendar.HOUR_OF_DAY)

val Calendar.minute: Int get() = this.get(Calendar.MINUTE)