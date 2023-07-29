package com.example.projectjavasimba.presentation.newsFragment.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.projectjavasimba.R
import com.example.projectjavasimba.domain.entity.EventEntity
import com.example.projectjavasimba.data_impl.NewsRepositoryImpl
import com.example.projectjavasimba.domain.usecase.NewsUseCase
import com.example.projectjavasimba.domain_impl.interactor.NewsInteractor
import com.example.projectjavasimba.repository.db.SimbaDataBase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch

class NewsViewModel(
    private val application: Application,
) : AndroidViewModel(application) {

    private val db = SimbaDataBase.getDatabaseNotes(application)
    private val useCase: NewsUseCase = NewsInteractor(NewsRepositoryImpl(db))

    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        message.postValue(application.getString(R.string.unknown_error))
        Log.d("GetError", throwable.message.toString())
    }
    private val coroutineScope = CoroutineScope(Dispatchers.IO + errorHandler)


    private val fullListEventEntity = MutableLiveData<List<EventEntity>>()
    val events = MutableLiveData<List<EventEntity>>()
    val message = MutableLiveData<String>()

    val progressLoader = MutableLiveData<Int>()
    val countNotReadEventEntity: MutableSharedFlow<List<EventEntity>> = MutableSharedFlow()

    fun setCategory(categoryId: Int) {
        fullListEventEntity.value?.filter { it.category == categoryId }?.let {
            events.postValue(it)
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

    fun getEvents(session: Boolean = true) {
        coroutineScope.launch {
            useCase.getEvents(application, session)
                .flowOn(Dispatchers.IO)
                .catch {
                    message.postValue(application.getString(R.string.unknown_error))
                }
                .collect {
                    it.events.let { result ->
                        if (result.isNotEmpty()) {
                            fullListEventEntity.postValue(result)
                            events.postValue(result)
                            updateListBadge(result)
                        } else {
                            message.postValue(application.getString(R.string.empty_events))
                        }
                    }
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}
