package com.example.simbakotlin.KotlinSecond

import java.util.Calendar
import java.util.Date

class User(
    private val id: Long,
    private val name: String,
    private val age:Int,
    private val type:Type
) {

    private val startTime:Long by lazy {
        System.currentTimeMillis()
    }

    fun getStartTime(){
        println("Время со старта: ${(System.currentTimeMillis() - startTime)/1000} секунды")
        Thread.sleep(2000)
        println("Время со старта: ${(System.currentTimeMillis() - startTime)/1000} секунды")
    }

    fun getAge() = age
    fun getType() = type
    fun getName() = name

}

