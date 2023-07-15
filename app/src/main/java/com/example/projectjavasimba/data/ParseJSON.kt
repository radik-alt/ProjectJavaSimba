package com.example.projectjavasimba.data

import android.content.Context
import android.util.Log
import com.example.projectjavasimba.data.callable.MyCallableCategory
import com.example.projectjavasimba.data.callable.MyCallableEvent
import com.example.projectjavasimba.data.entity.Category
import com.example.projectjavasimba.data.entity.EventEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class ParseJSON(
    private val context: Context,
) {

    fun parseEventJson(): Observable<ArrayList<EventEntity>> {
        return Observable.fromCallable {
            MyCallableEvent(context).call()
        }.zipWith(Observable.just(listOf(1, 2, 3, 4, 5))) { events, number ->
            val result = ArrayList<EventEntity>()
            for (one in events) {
                result.add(
                    EventEntity(
                        one.id,
                        one.title + number,
                        one.description,
                        one.listImage,
                        one.date,
                        one.street,
                        one.phone,
                        one.email,
                        one.category,
                        one.listFriends,
                        one.isRead
                    )
                )
            }
            result
        }.subscribeOn(Schedulers.io())
            .doOnNext {
                Log.d("LoggerData", it.toString())
            }
    }


    fun parseCategoryJson(): Observable<List<Category>> {
        return Observable.fromCallable {
            MyCallableCategory(context).call()
        }.subscribeOn(Schedulers.io())
    }

}