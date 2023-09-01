package com.example.projectjavasimba.presentation.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.common.cancelSession
import com.example.core.entity.EventEntity
import com.example.feature_events.service.DonatWorkManager
import com.example.projectjavasimba.R
import com.example.projectjavasimba.databinding.ActivityMainBinding
import com.example.projectjavasimba.di.SimbaApp
import com.example.projectjavasimba.presentation.main.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    @Inject
    lateinit var newsViewModel: MainViewModel

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

        intent?.getParcelableExtra<EventEntity>(DonatWorkManager.EVENT_ENTITY)?.let { event ->
            navController.navigate(
                com.example.feature_events.R.id.detail_fragment,
                Bundle().apply {
                    putParcelable("event", event)
                }
            )
        }
    }

    private fun observable() = with(newsViewModel) {
        lifecycleScope.launch {
            countNotReadEvents.collect { count ->
                findViewById<BottomNavigationView>(R.id.bottomNavigationView).let {
                    it.getOrCreateBadge(R.id.events_nav).let { badge ->
                        badge.number = count
                        badge.isVisible = count > 0
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        cancelSession(this)
        _binding = null
    }
}