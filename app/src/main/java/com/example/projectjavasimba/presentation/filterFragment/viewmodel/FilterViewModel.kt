package com.example.projectjavasimba.presentation.filterFragment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.projectjavasimba.R
import com.example.projectjavasimba.data.ParseJSON
import com.example.projectjavasimba.data.entity.Category
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

class FilterViewModel(
    private val application: Application
) : AndroidViewModel(application) {

    val listCategory = MutableLiveData<List<Category>>()
    val messageError = MutableLiveData<String>()

    fun getLoadData() {
        val myThread = Thread {
            try {
                val data = ParseJSON(getApplication())
                val request = data.parseCategoryJson()
                    .doOnError {
                        messageError.postValue(application.getString(R.string.error_network))
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { request ->
                        listCategory.postValue(request)
                    }

            } catch (e: Exception) {
                messageError.postValue(application.getString(R.string.unknow_error))
            }
        }
        myThread.start()
    }

}