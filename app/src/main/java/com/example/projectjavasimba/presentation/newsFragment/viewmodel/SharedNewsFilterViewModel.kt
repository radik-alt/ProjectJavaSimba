package com.example.projectjavasimba.presentation.newsFragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedNewsFilterViewModel : ViewModel() {

    val category = MutableLiveData<Int>()

}