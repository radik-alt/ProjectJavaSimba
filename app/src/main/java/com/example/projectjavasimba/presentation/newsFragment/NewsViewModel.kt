package com.example.projectjavasimba.presentation.newsFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projectjavasimba.data.ParseJSON
import com.example.projectjavasimba.data.entity.Category
import com.example.projectjavasimba.data.entity.Event

class NewsViewModel(
    application: Application,
) : AndroidViewModel(application) {

    private val listEvent = MutableLiveData<List<Event>>()
    private var filterCategory: Category? = null
    val progressLoader = MutableLiveData<Int>()

    fun setCategory(_category: Category) {
        filterCategory = _category
    }

    fun getListEvent(): LiveData<List<Event>> = listEvent

    fun getParseListEvent() {
        val myThread = Thread {
            for (i in 0..5) {
                Thread.sleep(1000)
                progressLoader.postValue(i * 20)
            }
            val data = ParseJSON(getApplication())
            val request = data.parseEventJson().get()
//                .filter { listEvent ->
//                listEvent.category.contains(filterCategory)
//            }
            listEvent.postValue(request)
        }
        myThread.start()
    }
}
