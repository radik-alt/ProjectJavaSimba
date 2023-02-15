package com.example.projectjavasimba.presentation.newsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectjavasimba.data.entity.Category

class SharedNewsFilterViewModel : ViewModel() {

    private val category = MutableLiveData<Category>()

    fun setCategory(changeCategory: Category) {
        category.value = changeCategory
    }

    fun getCategory(): LiveData<Category> = category


}