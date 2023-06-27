package com.example.projectjavasimba.presentation.helpFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.projectjavasimba.data.ParseJSON
import com.example.projectjavasimba.data.entity.Category

class HelpViewModel(application: Application) : AndroidViewModel(application) {

    val listCategory = MutableLiveData<List<Category>>()
    val progressLoader = MutableLiveData<Int>()

    fun setCategory(_list: List<Category>) {
        val myThread = Thread {
            for (i in 0..5) {
                Thread.sleep(1000)
                progressLoader.postValue(i * 20)
            }
            listCategory.postValue(_list)
        }
        myThread.start()
    }


    fun getParseListCategory() {
//        val data = ParseJSON(getApplication())
//        val request = data.parseCategoryJson().get()
//        if (request != null) {
//            setCategory(request)
//        }
    }

}