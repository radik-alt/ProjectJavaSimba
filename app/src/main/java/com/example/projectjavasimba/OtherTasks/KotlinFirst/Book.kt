package com.example.simbakotlin.KotlinFirst

import android.util.Log

class Book(override var price: Int, override var wordCount: Int) : Publication {

    override fun getType(): String {
        return when (wordCount){
            1000 -> "Flash Fiction"
            7500 -> "Short Story"
            else -> "Novel"
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            log("Equals по ссылке")
            return true
        } else {
            log("Unequals по ссылке")
        }

        if (javaClass != other?.javaClass) return false

        other as Book

        if (price != other.price && wordCount != other.wordCount) {
            log("Equal по значения не равны")
            return false
        } else {
            log("Equal по значения равны")
        }

        return true
    }

    private fun log(message:String){
        Log.d("BookLog", message)
    }

}