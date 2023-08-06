package com.example.common

import android.os.Build
import java.util.concurrent.ThreadLocalRandom
import kotlin.streams.asSequence

class Utils {

    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    fun randomStringByJavaRandom() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        ThreadLocalRandom.current()
            .ints(500.toLong(), 0, charPool.size)
            .asSequence()
            .map(charPool::get)
            .joinToString("")
    } else {
        ""
    }



}