package com.example.projectjavasimba.presentation.newsFragment.view

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.projectjavasimba.R
import com.example.projectjavasimba.data.entity.Event
import com.example.projectjavasimba.databinding.FragmentNewsBinding
import com.example.projectjavasimba.presentation.newsFragment.NewsAdapter.NewsAdapter
import com.example.projectjavasimba.common.utils.show
import com.example.projectjavasimba.presentation.newsFragment.viewmodel.NewsViewModel
import com.example.projectjavasimba.presentation.newsFragment.viewmodel.SharedNewsFilterViewModel
import com.example.projectjavasimba.service.ServiceGetData
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch


class NewsFragment : Fragment(), ServiceGetData.CallbackData<Event> {

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding
        get() = _binding ?: throw RuntimeException("FragmentNewsBinding == null")

    private val sharedNewsFilterViewModel: SharedNewsFilterViewModel by activityViewModels()
    private val newsViewModel: NewsViewModel by viewModels()
    private var callbackEvent: ServiceGetData.CallbackData<Event>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (binding.recyclerNews.adapter == null) {
            observable()
        }
        showBottomNavigation()
    }

    private fun observable() {
        newsViewModel.allowGetData.observe(this) {
            if (it) {
                val select = 1
                if (select == 1) {
                    newsViewModel.getParseListEvent()
                } else {
                    startService()
                }
                newsViewModel.allowGetData.value = false
            }
        }

        lifecycleScope.launch {
            newsViewModel.countNotReadEvent.collect { listCount ->
                val count = listCount.count { !it.isRead }
                requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
                    .let {
                        it.getOrCreateBadge(R.id.newsFragment).let { badge ->
                            badge.number = count
                            badge.isVisible = count > 0
                        }
                    }
            }
        }

        sharedNewsFilterViewModel.getCategory().observe(viewLifecycleOwner) { category ->
            newsViewModel.setCategory(category)
        }

        newsViewModel.progressLoader.observe(viewLifecycleOwner) { loader ->
            if (loader == 100) {
                binding.progressLoader.hide()
            } else {
                binding.progressLoader.progress += loader
                binding.progressLoader.show()
            }
        }

        newsViewModel.listEvent.observe(viewLifecycleOwner) { listEvent ->
            binding.recyclerNews.adapter.let { adapter ->
                if (adapter is NewsAdapter) {
                    adapter.update(listEvent)
                } else {
                    binding.recyclerNews.adapter =
                        NewsAdapter(listEvent) { event ->
                            if (!event.isRead) newsViewModel.updateItemBadge(event)
                            findNavController().navigate(
                                NewsFragmentDirections.actionNewsFragment2ToDetailFragment(event)
                            )
                        }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            toolbarNews.filter.setOnClickListener {
                findNavController().navigate(
                    NewsFragmentDirections.actionNewsFragment2ToFilterFragment()
                )
            }
        }
    }

    private fun showBottomNavigation() {
        val fragmentActivity = activity
        if (activity != null) {
            val bottom =
                fragmentActivity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            if (bottom != null && bottom.visibility == View.GONE) {
                bottom.show()
            }
        }
    }

    private fun startService() {
        val serviceIntent = Intent(requireContext(), ServiceGetData::class.java)
        requireContext().bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as? ServiceGetData.LocalBinder
            binder?.getService().let { service ->
                service?.callbackEvent = this@NewsFragment
                service?.getDataEvent()
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDataReceived(list: List<Event>) {
        newsViewModel.setDelayListEvent(list)
    }
}