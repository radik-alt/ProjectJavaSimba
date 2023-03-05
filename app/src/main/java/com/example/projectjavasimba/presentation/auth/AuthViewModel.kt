package com.example.projectjavasimba.presentation.auth

import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    fun authUser(email: String, password: String) {

    }

    private fun validAuth(email: String, password: String):Boolean {
        return true
    }

}