package com.example.simbakotlin.KotlinFirst




abstract interface Publication {

    var price:Int
    var wordCount:Int

    fun getType():String

}