package com.example.projectjavasimba

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.projectjavasimba.common.utils.hide
import com.example.projectjavasimba.common.utils.show
import com.example.projectjavasimba.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navBar = binding.bottomNavigationView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        navBar.setupWithNavController(navController)
    }

    fun changeVisibleBottomNavigation() {
        val bottom = binding.bottomNavigationView
        if (bottom.visibility == View.GONE) {
            bottom.show()
        } else {
            bottom.hide()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}