package com.example.projectjavasimba.presentation.newsFragment.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.projectjavasimba.data.ParseJSON
import com.example.projectjavasimba.data.entity.Category
import com.example.projectjavasimba.data.entity.Event
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    application: Application,
) : AndroidViewModel(application) {

    private var filterCategory: Category? = null
    private val fullListEvent = MutableLiveData<List<Event>>()

    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d("GetError", throwable.message.toString())
    }

    private val coroutineScope = CoroutineScope(Dispatchers.IO + errorHandler)

    val listEvent = MutableLiveData<List<Event>>()
    val progressLoader = MutableLiveData<Int>()
    val countNotReadEvent: MutableSharedFlow<List<Event>> = MutableSharedFlow()
    private val _countNotReadEvent: MutableSharedFlow<List<Event>> = MutableSharedFlow()
    val allowGetData = MutableLiveData(true)

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

    private fun setListEvent(list: List<Event>) {
        updateListBadge(list)
        listEvent.postValue(list)
    }

    fun setDelayListEvent(list: List<Event>) {
        val myThread = Thread {
            for (i in 0..5) {
                Thread.sleep(1000)
                progressLoader.postValue(i * 20)
            }
            updateListBadge(list)
            listEvent.postValue(list)
        }
        myThread.start()
    }

    private fun updateListBadge(events: List<Event>) {
        coroutineScope.launch {
            events.filter { event ->
                !event.isRead
            }.let { count ->
                countNotReadEvent.emit(count)
            }
        }
    }

    fun updateItemBadge(event: Event) {
        coroutineScope.launch {
            val resultList = mutableListOf<Event>()
            fullListEvent.value?.filter { item ->
                !item.isRead
            }?.forEach { _event ->
                _event.let {
                    if (it.id == event.id) {
                        event.isRead = true
                    } else {
                        resultList.add(_event)
                    }
                }
            }
            Log.d("GetCountNotRead", resultList.map { it.isRead }.toString())
            countNotReadEvent.emit(resultList)
        }
    }

    fun getParseListEvent() {
        coroutineScope.launch {
            ParseJSON(getApplication()).parseEventJson()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { request ->
                    fullListEvent.postValue(request)
                    val selectLoader = 0
                    if (selectLoader == 1) {
                        setDelayListEvent(request)
                    } else {
                        setListEvent(request)
                    }
                    updateListBadge(request)
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}
