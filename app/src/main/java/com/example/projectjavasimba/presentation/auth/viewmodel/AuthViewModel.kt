package com.example.projectjavasimba.presentation.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AuthViewModel @Inject constructor() : ViewModel() {

    private val isEnabled = MutableLiveData<Boolean>()
    val _isEnabled: LiveData<Boolean> = isEnabled

    private var validEmail: Boolean = false
    private var validPassword: Boolean = false

    fun setEmail(_email: String) {
        validEmail = _email.trim().length >= 6
        validAuth()
    }

    fun setPassword(_password: String) {
        validPassword = _password.trim().length >= 6
        validAuth()
    }

    fun validAuth() {
        isEnabled.value = validEmail && validPassword
    }

}