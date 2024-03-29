package com.example.feature_events.presentation.filter_news.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.entity.CategoryEntity
import com.example.feature_events.R
import com.example.feature_events.domain.usecase.FilterUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class FilterViewModel @Inject constructor(
    private val application: Application,
    private val useCase: FilterUseCase
) : ViewModel() {

    val listCategory = MutableLiveData<List<CategoryEntity>>()
    val messageError = MutableLiveData<String>()

    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        messageError.postValue(application.getString(R.string.unknown_error))
        Log.d("GetError", throwable.message.toString())
    }

    private val coroutineScope = CoroutineScope(Dispatchers.IO + errorHandler)

    fun getLoadData(session: Boolean = true) {
        coroutineScope.launch {
            useCase.getCategory(application, session)
                .catch {
                    messageError.postValue(application.getString(R.string.error_network))
                }
                .collect { request ->
                    listCategory.postValue(request.categories)
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

}