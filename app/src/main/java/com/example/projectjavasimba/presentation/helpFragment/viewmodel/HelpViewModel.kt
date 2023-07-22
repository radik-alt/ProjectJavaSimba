package com.example.projectjavasimba.presentation.helpFragment.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.projectjavasimba.R
import com.example.projectjavasimba.data.ParseJSON
import com.example.projectjavasimba.data_impl.HelpRepositoryImpl
import com.example.projectjavasimba.domain.entity.Category
import com.example.projectjavasimba.domain.entity.CategoryEntity
import com.example.projectjavasimba.domain_impl.interactor.HelpInteractor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

class HelpViewModel(
    private val application: Application
) : AndroidViewModel(application) {

    val messageError = MutableLiveData<String>()
    val listCategory = MutableLiveData<List<CategoryEntity>>()

    private val repository = HelpRepositoryImpl()

    @SuppressLint("CheckResult")
    fun getParseListCategory() {
        try {
            repository.getCategory(application)
                .doOnError {
                    messageError.postValue(application.getString(R.string.error_network))
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { request ->
                    listCategory.postValue(request.categories)
                }

        } catch (e: Exception) {
            messageError.postValue(application.getString(R.string.unknow_error))
        }
    }
}
