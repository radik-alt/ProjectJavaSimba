package com.example.feature_events.presentation.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedNewsFilterViewModel : ViewModel() {

    val category = MutableLiveData<Int>()

}