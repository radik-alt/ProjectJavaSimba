package com.example.projectjavasimba.BlockTwoJava

import android.util.Log
import com.example.simbakotlin.KotlinFirst.Book
import com.example.simbakotlin.KotlinFirst.Magazine
import com.example.simbakotlin.KotlinFirst.Publication

class MainKotlin {

    fun main(){
        first()
    }

    private fun first(){
        var publication: Publication = Magazine(100, 1000)
        println("Magazine: кол-во строк = ${publication.wordCount}, цена в евро = ${covertRubleToEuro(publication.price)}, type = ${publication.getType()}")

        val equalFirst = publication == Magazine(100, 1000)

        publication = Book(100, 1000)
        println("Book: кол-во строк = ${publication.wordCount}, цена в евро = ${covertRubleToEuro(publication.price)}, type = ${publication.getType()}")

        val equalSecond = publication == Book(100, 1000)

        println(equalFirst)
        println(equalSecond)

        val sum = {a:Int, b:Int -> a + b}

        buy(Magazine(300, 2000))
        buy(null)

        log(FIRST_TASK, sum(1, 2).toString())
    }

    private fun covertRubleToEuro(price: Int): Double = price / courseEuro

    private fun log(log:String, message:String){
        Log.d(log, message)
    }

    private fun buy (publication: Publication?){
        publication?.let {
            println("The purchase is complete. The purchase amount was ${it.price}")
        }
    }

    companion object{
        const val FIRST_TASK = "FIRST_TASK_LOG"
        const val SECOND_TASK = "SECOND_TASK_LOG"
        private var courseEuro = 76.64
    }


}