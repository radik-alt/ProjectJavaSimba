package com.example.feature_events.presentation.newsFragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedNewsFilterViewModel : ViewModel() {

    val category = MutableLiveData<Int>()

}