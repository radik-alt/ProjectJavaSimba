package com.example.projectjavasimba.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.projectjavasimba.R
import com.example.projectjavasimba.common.utils.cancelSession
import com.example.projectjavasimba.databinding.ActivityMainBinding
import com.example.projectjavasimba.di.SimbaApp
import com.example.projectjavasimba.presentation.newsFragment.viewmodel.NewsViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    @Inject
    lateinit var newsViewModel: NewsViewModel

    private val component by lazy {
        (application as SimbaApp).component
    }

    override fun onResume() {
        super.onResume()
        cancelSession(this)
        newsViewModel.getEvents()
        observable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)

        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navBar = binding.bottomNavigationView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        navBar.setupWithNavController(navController)
    }

    private fun observable() = with(newsViewModel) {
        lifecycleScope.launch {
            countNotReadEventEntity.collect { listCount ->
                val count = listCount.count { !it.isRead }
                findViewById<BottomNavigationView>(R.id.bottomNavigationView).let {
                    it.getOrCreateBadge(R.id.newsFragment).let { badge ->
                        badge.number = count
                        badge.isVisible = count > 0
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelSession(this)
        _binding = null
    }
}