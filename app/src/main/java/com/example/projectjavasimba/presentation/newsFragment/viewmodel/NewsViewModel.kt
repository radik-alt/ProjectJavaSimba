package com.example.projectjavasimba.presentation.newsFragment.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.projectjavasimba.R
import com.example.projectjavasimba.data.ParseJSON
import com.example.projectjavasimba.domain.entity.Category
import com.example.projectjavasimba.domain.entity.EventEntity
import com.example.projectjavasimba.data_impl.NewsRepositoryImpl
import com.example.projectjavasimba.domain.usecase.NewsUseCase
import com.example.projectjavasimba.domain_impl.interactor.NewsInteractor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val application: Application,
) : AndroidViewModel(application) {

    private var filterCategory: Category? = null
    private val fullListEventEntity = MutableLiveData<List<EventEntity>>()

    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d("GetError", throwable.message.toString())
    }

    private val useCase: NewsUseCase = NewsInteractor(NewsRepositoryImpl())

    private val coroutineScope = CoroutineScope(Dispatchers.IO + errorHandler)

    val events = MutableLiveData<List<EventEntity>>()
    val progressLoader = MutableLiveData<Int>()
    val countNotReadEventEntity: MutableSharedFlow<List<EventEntity>> = MutableSharedFlow()
    private val _countNotReadEventEntity: MutableSharedFlow<List<EventEntity>> = MutableSharedFlow()
    val allowGetData = MutableLiveData(true)

    val message = MutableLiveData<String>()
    fun setCategory(_category: Category) {
        filterCategory = _category
//        filterList()
    }

//    private fun filterList() {
//        listEventEntity.value = fullListEventEntity.value
//        listEventEntity.value?.filter {
//            it.category.contains(filterCategory)
//        }.let { list ->
//            setListEvent(list ?: emptyList())
//        }
//    }

    private fun setListEvent(list: List<EventEntity>) {
        updateListBadge(list)
        events.postValue(list)
    }

    fun setDelayListEvent(list: List<EventEntity>) {
        val myThread = Thread {
            for (i in 0..5) {
                Thread.sleep(1000)
                progressLoader.postValue(i * 20)
            }
            updateListBadge(list)
            events.postValue(list)
        }
        myThread.start()
    }

    private fun updateListBadge(eventEntities: List<EventEntity>) {
        coroutineScope.launch {
            eventEntities.filter { event ->
                !event.isRead
            }.let { count ->
                countNotReadEventEntity.emit(count)
            }
        }
    }

    fun updateItemBadge(eventEntity: EventEntity) {
        coroutineScope.launch {
            val resultList = mutableListOf<EventEntity>()
            fullListEventEntity.value?.filter { item ->
                !item.isRead
            }?.forEach { _event ->
                _event.let {
                    if (it.id == eventEntity.id) {
                        eventEntity.isRead = true
                    } else {
                        resultList.add(_event)
                    }
                }
            }
            countNotReadEventEntity.emit(resultList)
        }
    }

    @SuppressLint("CheckResult")
    fun getEvents() {
        useCase.getEvents()
            .observeOn(Schedulers.io())
            .doOnError {
                getFromJsonEvents()
            }
            .subscribe {
                it.events.let { result ->
                    if (result.isNotEmpty()) {
                        events.postValue(it.events)
                        updateListBadge(it.events)
                    } else {
                        message.postValue("Список новостей пуст!")
                    }
                }

            }
    }

    @SuppressLint("CheckResult")
    fun getFromJsonEvents() {
        ParseJSON(getApplication()).parseEventJson()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                message.postValue(application.getString(R.string.unknown_error))
            }
            .subscribe { request ->
                fullListEventEntity.postValue(request)
                val selectLoader = 0
                if (selectLoader == 1) {
                    setDelayListEvent(request)
                } else {
                    setListEvent(request)
                }
                updateListBadge(request)
            }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}
