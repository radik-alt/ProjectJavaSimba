package com.example.projectjavasimba.presentation.newsFragment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.projectjavasimba.R
import com.example.projectjavasimba.domain.entity.EventEntity
import com.example.projectjavasimba.databinding.FragmentNewsBinding
import com.example.projectjavasimba.presentation.newsFragment.NewsAdapter.NewsAdapter
import com.example.projectjavasimba.common.utils.show
import com.example.projectjavasimba.presentation.newsFragment.viewmodel.NewsViewModel
import com.example.projectjavasimba.presentation.newsFragment.viewmodel.SharedNewsFilterViewModel
import com.example.projectjavasimba.service.ServiceGetData
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch


class NewsFragment : Fragment(), ServiceGetData.CallbackData<EventEntity> {

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding
        get() = _binding ?: throw RuntimeException("FragmentNewsBinding == null")

    private val sharedNewsFilterViewModel: SharedNewsFilterViewModel by activityViewModels()
    private val newsViewModel: NewsViewModel by viewModels()

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

    private fun observable() = with(newsViewModel) {
        allowGetData.observe(this@NewsFragment) {
            newsViewModel.getEvents()
            newsViewModel.allowGetData.value = false
        }

        progressLoader.observe(this@NewsFragment) { loader ->
            if (loader == 100) {
                binding.progressLoader.hide()
            } else {
                binding.progressLoader.progress += loader
                binding.progressLoader.show()
            }
        }

        events.observe(this@NewsFragment) { listEvent ->
            binding.recyclerNews.adapter.let { adapter ->
                if (adapter is NewsAdapter) {
                    adapter.update(listEvent)
                } else {
                    binding.recyclerNews.adapter = NewsAdapter(listEvent) { event ->
                        if (!event.isRead) newsViewModel.updateItemBadge(event)
                        findNavController().navigate(
                            NewsFragmentDirections.actionNewsFragment2ToDetailFragment(event)
                        )
                    }
                }
            }
        }

        lifecycleScope.launch {
            countNotReadEventEntity.collect { listCount ->
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbarNews) {
            filter.setOnClickListener {
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDataReceived(list: List<EventEntity>) {
        newsViewModel.setDelayListEvent(list)
    }
}