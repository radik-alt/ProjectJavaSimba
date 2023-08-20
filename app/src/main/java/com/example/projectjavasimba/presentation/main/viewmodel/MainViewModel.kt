package com.example.projectjavasimba.presentation.main.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectjavasimba.domain.usecase.MainUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val application: Application,
    private val useCase: MainUseCase
) : ViewModel() {

    val countNotReadEvents: MutableSharedFlow<Int> = MutableSharedFlow()

    fun getEvents(session: Boolean = true) {
        viewModelScope.launch {
            useCase.getEvents(application, session)
                .flowOn(Dispatchers.IO)
                .catch {
                    countNotReadEvents.emit(0)
                }
                .collect {
                    it.events.let { response ->
                        if (response.isNotEmpty()) {
                            val listNotRead = response.filter { event -> !event.isRead }
                            countNotReadEvents.emit(listNotRead.size)
                        } else {
                            countNotReadEvents.emit(0)
                        }
                    }
                }
        }
    }
}