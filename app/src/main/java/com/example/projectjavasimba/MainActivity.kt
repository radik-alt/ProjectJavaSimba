package com.example.projectjavasimba

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val s = "ic_launcher_foreground"
        Log.d("GenIntImage", R.drawable.ic_launcher_foreground.toString())
    }
}