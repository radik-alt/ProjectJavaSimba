package com.example.search.presentation.searchFragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SearchViewPagerViewModel: ViewModel() {

    val searchMeeting = MutableLiveData<String?>()

}