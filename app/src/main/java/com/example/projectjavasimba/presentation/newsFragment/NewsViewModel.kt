package com.example.projectjavasimba.presentation.newsFragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.projectjavasimba.data.ParseJSON
import com.example.projectjavasimba.data.entity.Category
import com.example.projectjavasimba.data.entity.Event
import io.reactivex.rxjava3.subjects.PublishSubject

class NewsViewModel(
    application: Application,
) : AndroidViewModel(application) {

    private var filterCategory: Category? = null
    private val fullListEvent = MutableLiveData<List<Event>>()

    val listEvent = MutableLiveData<List<Event>>()

    val progressLoader = MutableLiveData<Int>()
    val newsSubject: PublishSubject<List<Event>> = PublishSubject.create()
    val allowGetData = MutableLiveData<Boolean>(true)

    fun setCategory(_category: Category) {
        filterCategory = _category
        filterList()
    }

    private fun filterList() {
        listEvent.value = fullListEvent.value
        listEvent.value?.filter {
            it.category.contains(filterCategory)
        }.let { list ->
            setListEvent(list ?: emptyList())
        }
    }

    fun setListEvent(list: List<Event>) {
        newsSubject.onNext(list)
        listEvent.postValue(list)
    }

    fun setDelayListEvent(list: List<Event>) {
        val myThread = Thread {
            for (i in 0..5) {
                Thread.sleep(1000)
                progressLoader.postValue(i * 20)
            }
            newsSubject.onNext(list)
            listEvent.postValue(list)
        }
        myThread.start()
    }

    fun getParseListEvent() {
        val data = ParseJSON(getApplication())
        val request = data.parseEventJson().get()
        fullListEvent.value = request
        newsSubject.onNext(request)

        val selectLoader = 0
        if (selectLoader == 1) {
            setDelayListEvent(request)
        } else {
            setListEvent(request)
        }
    }


}
