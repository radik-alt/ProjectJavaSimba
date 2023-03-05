package com.example.projectjavasimba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import com.example.projectjavasimba.databinding.ActivityAuthBinding
import com.example.projectjavasimba.presentation.auth.AuthViewModel
import io.reactivex.rxjava3.core.Observable

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val authViewModel: AuthViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            btnAuth.setOnClickListener {
                authViewModel.authUser(
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString()
                )
            }
        }

    }

}