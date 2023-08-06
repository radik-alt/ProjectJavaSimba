package com.example.search.presentation.meetingFragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MeetingViewModel : ViewModel() {

    val meeting = MutableLiveData<List<String>>()
    val errorMessage = MutableLiveData<String>()

    private val fullMeeting = MutableLiveData<List<String>>()

    fun getMeeting() {
        val listMeeting = listOf(
            "First",
            "Second",
            "Третье",
            "Четвертое",
            "Пятое",
            )

        fullMeeting.postValue(listMeeting)
        meeting.postValue(listMeeting)
    }

    fun getMeetingByFilter(findString: String) {
        if (findString.isNotEmpty()) {
            fullMeeting.value?.let {
                it.filter { it.lowercase().contains(findString) }.let { result ->
                    if (result.isNotEmpty()) {
                        meeting.postValue(result)
                    } else {
                        errorMessage.postValue("Такого события нет")
                    }
                }
            }
        } else {
            meeting.postValue(fullMeeting.value)
        }
    }
}