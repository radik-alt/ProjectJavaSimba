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


    private val useCase: NewsUseCase = NewsInteractor(NewsRepositoryImpl())

    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d("GetError", throwable.message.toString())
    }
    private val coroutineScope = CoroutineScope(Dispatchers.IO + errorHandler)


    private val fullListEventEntity = MutableLiveData<List<EventEntity>>()
    val events = MutableLiveData<List<EventEntity>>()
    val message = MutableLiveData<String>()

    val progressLoader = MutableLiveData<Int>()
    val countNotReadEventEntity: MutableSharedFlow<List<EventEntity>> = MutableSharedFlow()

    fun setCategory(categoryId: Int) {
        fullListEventEntity.value?.filter { it.category == categoryId }.let {
            events.value = it
        }
    }

    private fun updateListBadge(eventEntities: List<EventEntity>) {
        coroutineScope.launch {
            eventEntities.filter { event -> !event.isRead }.let { count ->
                countNotReadEventEntity.emit(count)
            }
        }
    }

    fun updateItemBadge(eventEntity: EventEntity) {
        coroutineScope.launch {
            val resultList = mutableListOf<EventEntity>()
            fullListEventEntity.value?.filter { item -> !item.isRead }?.forEach { _event ->
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
        useCase.getEvents(application)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnError {
                message.postValue(application.getString(R.string.unknown_error))
            }
            .subscribe {
                it.events.let { result ->
                    if (result.isNotEmpty()) {
                        events.postValue(it.events)
                        updateListBadge(it.events)
                    } else {
                        message.postValue(application.getString(R.string.empty_events))
                    }
                }
            }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}
