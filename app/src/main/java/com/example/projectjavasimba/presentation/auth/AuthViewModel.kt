package com.example.projectjavasimba.presentation.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    val isEnabled = MutableLiveData<Boolean>()
    private var email:String = ""
    private var password:String = ""

    fun setEmail(_email:String) {
        email = _email
    }

    fun setPassword(_password:String) {
        password = _password
    }

    fun validAuth() {
        isEnabled.value = email.trim().length >= 6 && password.trim().length >= 6
    }

}