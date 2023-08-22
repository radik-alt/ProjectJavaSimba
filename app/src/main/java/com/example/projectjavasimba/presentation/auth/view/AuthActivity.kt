package com.example.projectjavasimba.presentation.auth.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        setContent {
            MaterialTheme {
                LazyColumn {
                    item {
                        AppBar()
                    }
                }
            }
        }
//        binding.run {
//            if (BuildConfig.DEBUG) {
//                etEmail.setText("Radik.app")
//                etPassword.setText("12345678")
//            }
//
//            btnAuth.setOnClickListener {
//                startActivity(Intent(this@AuthActivity, MainActivity::class.java))
//            }
//
//            etEmail.setOnLongClickListener {
//                etEmail.setText("Radik.app")
//                etPassword.setText("12345678")
//                true
//            }
//        }
//
//        RxTextView.textChanges(binding.etEmail)
//            .mergeWith(RxTextView.textChanges(binding.etPassword))
//            .debounce(400, TimeUnit.MILLISECONDS)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { email ->
//                authViewModel.setEmail(email.toString())
//                authViewModel.validAuth()
//            }
//
//
//        RxTextView.textChanges(binding.etPassword)
//            .debounce(400, TimeUnit.MILLISECONDS)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { password ->
//                authViewModel.setPassword(password.toString())
//                authViewModel.validAuth()
//            }
    }

    private fun observable() {
//        authViewModel.isEnabled.observe(this) { enabled ->
//            if (enabled) {
//                binding.btnAuth.isEnabled = true
//                binding.btnAuth.background = getDrawable(R.drawable.button_background)
//            } else {
//                binding.btnAuth.isEnabled = false
//                binding.btnAuth.background = getDrawable(R.drawable.dont_view_btn_background)
//            }
//        }
    }

    @Composable
    fun AppBar() {
        Row(modifier = Modifier.fillMaxWidth().padding(15.dp).background(Color.Green)) {
            Text(
                "Регистрация",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 15.sp,
            )
        }
    }


}