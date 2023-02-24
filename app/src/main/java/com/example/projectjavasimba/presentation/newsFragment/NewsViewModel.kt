package com.example.projectjavasimba.presentation.newsFragment

import android.app.Application
import android.util.Log
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
    val isLoader = MutableLiveData<Boolean>()

    fun setCategory(_category: Category) {
        filterCategory = _category
    }

    fun getListEvent(): LiveData<List<Event>> = listEvent

    fun getParseListEvent() {
        isLoader.postValue(false)
        val myThread = Thread {
            Thread.sleep(5000)
            val data = ParseJSON(getApplication())
            val request = data.parseEventJson().get().filter { listEvent ->
                listEvent.category.contains(filterCategory)
            }
            listEvent.postValue(request)
            isLoader.postValue(true)
        }
        myThread.start()
    }
}
