package com.example.projectjavasimba

import android.util.Log
import com.example.simbakotlin.KotlinFirst.Book
import com.example.simbakotlin.KotlinFirst.Magazine
import com.example.simbakotlin.KotlinFirst.Publication
import com.example.simbakotlin.KotlinSecond.AuthCallback
import com.example.simbakotlin.KotlinSecond.Type
import com.example.simbakotlin.KotlinSecond.User
import kotlin.random.Random

class MainKotlin {

    fun main(){
        first()
    }

    private fun first(){
        var publication: Publication = Magazine(100, 1000)
        println("Magazine: кол-во строк = ${publication.wordCount}, цена в евро = ${covertRubleToEuro(publication.price ?: 0)}, type = ${publication.getType()}")

        val equalFirst = publication == Book(100, 1000)

        publication = Book(100, 1000)
        println("Book: кол-во строк = ${publication.wordCount}, цена в евро = ${covertRubleToEuro(publication.price ?: 0)}, type = ${publication.getType()}")

        val equalSecond = publication == Book(100, 1000)

        if (equalFirst === equalSecond){
            log(FIRST_TASK, "Обьекты equalFirst и equalSecond равны по ссылке")
        } else {
            log(FIRST_TASK, "Обьекты equalFirst и equalSecond не равны по ссылке")
        }

        equalFirst == equalSecond

        val sum = {a:Int, b:Int -> print(a + b)}

        buy(Magazine(null, 2000))
        buy(Magazine(1000, 2000))

        sum(1, 2)
    }

    private fun covertRubleToEuro(price: Int): Double = price / courseEuro



    private fun buy (publication: Publication){
        publication.price.let {
            println("The purchase is complete. The purchase amount was $it")
        }
    }

    private fun two(){
        val listUser = ArrayList<User>()
        val user = User(0, "Радик", 20, Type.FULL)
        listUser.add(user)
        listUser.apply {
            for (i in 1 until 4){
                val type = if (i % 2 == 0) Type.DEMO else Type.FULL
                add((User(i.toLong(), "User $i", Random.nextInt(19, 30), type)))
            }
        }

        user.getStartTime()

        for (i in listUser){
            if (i.validAge(i.getAge()))
                throw Exception("Юзеры меньше 18!!!")
        }

        println("Список юзеров с типом FULL: ")
        val listUserFullType = listUser.filter {
            it.getType() == Type.FULL
        }
        println(listUserFullType)

        val changeList = listUser.map {
            it.getName()
        }
        println(changeList)
        log("TwoTaskLog", "Первый элемент списка: ${changeList.first()}. Послдений элемент списка: ${changeList.last()}")


        auth {
            if (user.validAge(user.getAge())){
                println(it)
                authCallback.authSuccess()
            }
            else{
                authCallback.authFailed()
            }
        }

        val userAction = listUser.first()
        doAction(Action.Logout(userAction))
    }

    private val authCallback = object : AuthCallback {
        override fun authSuccess() {
            log(SECOND_TASK, "Усешная авториазация!")
        }

        override fun authFailed() {
            log(SECOND_TASK, "Ошибка авториазации!")
        }
    }

    private inline fun auth(updateCache: (message: String) -> Unit) {
        updateCache.invoke("Обновления кэша")
    }


    private fun doAction(action: Action) = when (action){
        is Action.Registration -> log(SECOND_TASK, "Регистрация юзера")
        is Action.Login -> log(SECOND_TASK, "Юзера авторизовался")
        is Action.Logout -> log(SECOND_TASK, "Юзер вышел")
        else -> {log(SECOND_TASK, "Сервак упал... :(")}
    }

    private fun log(logName:String, message:String){
        Log.d(logName, message)
        println(message)
    }

    private fun User.validAge(age:Int) = age <= 18

    companion object{
        const val FIRST_TASK = "FIRST_TASK_LOG"
        const val SECOND_TASK = "SECOND_TASK_LOG"
        private var courseEuro = 76.64
}
    }

sealed class Action {
    class Registration(user: User): Action()
    class Login(user: User):Action()
    class Logout(user: User):Action()
}