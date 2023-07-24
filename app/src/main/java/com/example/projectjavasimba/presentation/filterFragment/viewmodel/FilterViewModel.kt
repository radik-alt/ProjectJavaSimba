package com.example.projectjavasimba.presentation.filterFragment.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.projectjavasimba.R
import com.example.projectjavasimba.data.ParseJSON
import com.example.projectjavasimba.data_impl.FilterRepositoryImpl
import com.example.projectjavasimba.domain.entity.Category
import com.example.projectjavasimba.domain.entity.CategoryEntity
import com.example.projectjavasimba.domain_impl.interactor.FilterInteractor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.lang.Exception

class FilterViewModel(
    private val application: Application
) : AndroidViewModel(application) {

    val listCategory = MutableLiveData<List<CategoryEntity>>()
    val messageError = MutableLiveData<String>()

    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        messageError.postValue(application.getString(R.string.unknown_error))
        Log.d("GetError", throwable.message.toString())
    }

    private val coroutineScope = CoroutineScope(Dispatchers.IO + errorHandler)
    private val useCase = FilterInteractor(FilterRepositoryImpl())

    fun getLoadData() {
        coroutineScope.launch {
            useCase.getCategory(application)
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