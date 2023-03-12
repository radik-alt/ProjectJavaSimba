package com.example.projectjavasimba.data

import android.content.Context
import com.example.projectjavasimba.data.callable.MyCallableCategory
import com.example.projectjavasimba.data.callable.MyCallableEvent
import com.example.projectjavasimba.data.entity.Category
import com.example.projectjavasimba.data.entity.Event
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class ParseJSON(
    private val context: Context,
) {

    fun parseEventJson(): Future<List<Event>> {
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        val future = executor.submit(MyCallableEvent(context))
        executor.shutdown()
        return future
    }

    fun parseCategoryJson(): Future<List<Category>> {
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        val future = executor.submit(MyCallableCategory(context))
        executor.shutdown()
        return future
    }

}