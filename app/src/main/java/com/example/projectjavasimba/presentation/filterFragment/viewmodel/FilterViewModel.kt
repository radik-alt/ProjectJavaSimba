package com.example.projectjavasimba.presentation.filterFragment.viewmodel

import android.annotation.SuppressLint
import android.app.Application
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
import java.lang.Exception

class FilterViewModel(
    private val application: Application
) : AndroidViewModel(application) {

    val listCategory = MutableLiveData<List<CategoryEntity>>()
    val messageError = MutableLiveData<String>()

    private val useCase = FilterInteractor(FilterRepositoryImpl())

    @SuppressLint("CheckResult")
    fun getLoadData() {
        try {
            useCase.getCategory(application)
                .doOnError {
                    messageError.postValue(application.getString(R.string.error_network))
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result ->
                    listCategory.postValue(result.categories)
                }

        } catch (e: Exception) {
            messageError.postValue(application.getString(R.string.unknow_error))
        }
    }

}