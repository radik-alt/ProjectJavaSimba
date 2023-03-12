package com.example.projectjavasimba.presentation.auth

import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    fun authUser(email: String, password: String) {
        if (validAuth(email, password)){

        }
    }

    fun validAuth(email: String, password: String):Boolean {
        return email.length < 6 || password.length < 6
    }

}