package com.example.projectjavasimba.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectjavasimba.R
import com.example.projectjavasimba.presentation.auth.view.AuthActivity
import kotlinx.coroutines.*

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            startActivity(Intent(this@SplashScreen, AuthActivity::class.java))
            finish()
        }
    }
}