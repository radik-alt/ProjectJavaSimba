package com.example.projectjavasimba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.projectjavasimba.R
import com.example.projectjavasimba.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding?=null
    private val binding:ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*val navView:BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragmentContainerView)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.profileFragment,
            R.id.searchFragment,
            R.id.helpFragment,
            R.id.helpFragment,
            R.id.helpFragment
        ))

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)*/

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}