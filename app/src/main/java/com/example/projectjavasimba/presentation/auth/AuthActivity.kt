package com.example.projectjavasimba.presentation.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.projectjavasimba.R
import com.example.projectjavasimba.databinding.ActivityAuthBinding
import com.example.projectjavasimba.presentation.MainActivity
import com.jakewharton.rxbinding.widget.RxTextView
import io.reactivex.rxjava3.core.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            btnAuth.setOnClickListener {
                startActivity(Intent(this@AuthActivity, MainActivity::class.java))
            }
        }


        RxTextView.textChanges(binding.etEmail)
            .mergeWith(RxTextView.textChanges(binding.etPassword))
            .debounce(400, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.length >= 6) {
                    binding.btnAuth.isEnabled = true
                    binding.btnAuth.background = getDrawable(R.drawable.button_background)
                }
            }


        RxTextView.textChanges(binding.etPassword)
            .debounce(400, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.length >= 6) {
                    binding.btnAuth.isEnabled = true
                    binding.btnAuth.background = getDrawable(R.drawable.button_background)
                }
            }


    }

}