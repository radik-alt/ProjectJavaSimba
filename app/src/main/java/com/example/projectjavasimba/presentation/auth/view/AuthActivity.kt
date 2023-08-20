package com.example.projectjavasimba.presentation.auth.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.auth.R
import com.example.auth.databinding.ActivityAuthBinding
import com.example.projectjavasimba.presentation.auth.viewmodel.AuthViewModel
import com.example.core.BuildConfig
import com.example.projectjavasimba.presentation.main.view.MainActivity
import com.jakewharton.rxbinding.widget.RxTextView
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        observable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            if (BuildConfig.DEBUG) {
                etEmail.setText("Radik.app")
                etPassword.setText("12345678")
            }

            btnAuth.setOnClickListener {
                startActivity(Intent(this@AuthActivity, MainActivity::class.java))
            }

            etEmail.setOnLongClickListener {
                etEmail.setText("Radik.app")
                etPassword.setText("12345678")
                true
            }
        }

        RxTextView.textChanges(binding.etEmail)
            .mergeWith(RxTextView.textChanges(binding.etPassword))
            .debounce(400, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { email ->
                authViewModel.setEmail(email.toString())
                authViewModel.validAuth()
            }


        RxTextView.textChanges(binding.etPassword)
            .debounce(400, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { password ->
                authViewModel.setPassword(password.toString())
                authViewModel.validAuth()
            }
    }

    private fun observable() {
        authViewModel.isEnabled.observe(this) { enabled ->
            if (enabled) {
                binding.btnAuth.isEnabled = true
                binding.btnAuth.background = getDrawable(R.drawable.button_background)
            } else {
                binding.btnAuth.isEnabled = false
                binding.btnAuth.background = getDrawable(R.drawable.dont_view_btn_background)
            }
        }
    }

}