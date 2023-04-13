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

    val listEvent = MutableLiveData<List<Event>>()
    private var filterCategory: Category? = null
    val progressLoader = MutableLiveData<Int>()

    fun setCategory(_category: Category) {
        filterCategory = _category
    }

    fun setListEvent(list: List<Event>) {
        val myThread = Thread {
            for (i in 0..5) {
                Thread.sleep(1000)
                progressLoader.postValue(i * 20)
            }
            listEvent.postValue(list)
        }
        myThread.start()
    }

    fun getParseListEvent() {
        val data = ParseJSON(getApplication())
        val request = data.parseEventJson().get()
            .filter { listEvent ->
                listEvent.category.contains(filterCategory)
            }
        setListEvent(request)
    }
}
