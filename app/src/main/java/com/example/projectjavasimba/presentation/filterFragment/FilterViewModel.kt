package com.example.projectjavasimba.presentation.filterFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.projectjavasimba.data.ParseJSON
import com.example.projectjavasimba.data.entity.Category

class FilterViewModel(
    application: Application
) : AndroidViewModel(application) {

    val listCategory = MutableLiveData<List<Category>>()
    fun getListCategory () {
        val list = ParseJSON(getApplication()).parseCategoryJson().get()
        listCategory.postValue(list)
    }

}