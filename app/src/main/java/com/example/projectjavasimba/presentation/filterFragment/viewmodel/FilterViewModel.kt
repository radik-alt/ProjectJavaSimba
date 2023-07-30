package com.example.projectjavasimba.presentation.filterFragment.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectjavasimba.R
import com.example.projectjavasimba.data_impl.FilterRepositoryImpl
import com.example.projectjavasimba.domain.entity.CategoryEntity
import com.example.projectjavasimba.domain.usecase.FilterUseCase
import com.example.projectjavasimba.domain_impl.interactor.FilterInteractor
import com.example.repository.api.PostmanApi
import com.example.repository.db.SimbaDataBase
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