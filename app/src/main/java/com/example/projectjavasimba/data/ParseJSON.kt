package com.example.projectjavasimba.data

import android.content.Context
import android.util.Log
import com.example.projectjavasimba.data.callable.MyCallableCategory
import com.example.projectjavasimba.data.callable.MyCallableEvent
import com.example.projectjavasimba.domain.entity.Category
import com.example.projectjavasimba.domain.entity.EventEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.Date

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
                        id = one.id ?: -1,
                        title = one.title ?: "",
                        description = one.description ?: "",
                        listImage = one.listImage ?: arrayListOf(),
                        createAt = one.createAt ?: Date(),
                        street = one.street ?: "",
                        status = one.status ?: -1,
                        startDate = one.startDate ?: Date(),
                        endDate = one.endDate ?: Date(),
                        phone = one.phone ?: "",
                        category = one.category ?: -1,
                        isRead = false
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