package com.example.projectjavasimba.presentation.helpFragment.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.projectjavasimba.R
import com.example.projectjavasimba.data_impl.HelpRepositoryImpl
import com.example.projectjavasimba.domain.entity.CategoryEntity
import com.example.projectjavasimba.domain_impl.interactor.HelpInteractor
import com.example.projectjavasimba.repository.db.SimbaDataBase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HelpViewModel(
    private val application: Application
) : AndroidViewModel(application) {

    val messageError = MutableLiveData<String>()
    val listCategory = MutableLiveData<List<CategoryEntity>>()

    private val db = SimbaDataBase.getDatabaseNotes(application)
    private val useCase = HelpInteractor(HelpRepositoryImpl(db))

    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        messageError.postValue(application.getString(R.string.unknown_error))
        Log.d("GetError", throwable.message.toString())
    }

    private val coroutineScope = CoroutineScope(Dispatchers.IO + errorHandler)

    fun getParseListCategory(session: Boolean = true) {
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
