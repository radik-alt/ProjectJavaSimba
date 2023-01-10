package com.example.simbakotlin.KotlinFirst

import android.util.Log
import com.example.projectjavasimba.MainKotlin

class Book(override var price: Int?, override var wordCount: Int) : Publication {

    override fun getType(): String {
        return when {
            wordCount <= 1000 -> "Flash Fiction"
            wordCount <= 7500 -> "Short Story"
            else -> "Novel"
        }
    }

    override fun equals(other: Any?): Boolean {
        other as Book

        if (price != other.price && wordCount != other.wordCount) {
            log("Equal по полям не равны")
            return false
        } else {
            log("Equal по полям равны")
        }

        return true
    }

    private fun log(message:String){
        Log.d(MainKotlin.FIRST_TASK, message)
    }

}