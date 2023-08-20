package com.example.feature_events.presentation.news.view

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.feature_events.presentation.news.news_adapter.NewsAdapter
import com.example.base.MessageAdapter.MessageAdapter
import com.example.base.placeholder.PlaceHolderAdapter
import com.example.common.SESSION_EVENTS
import com.example.common.isFirstEnter
import com.example.feature_events.databinding.FragmentNewsBinding
import com.example.feature_events.di.EventComponentProvider
import com.example.feature_events.presentation.news.viewmodel.NewsViewModel
import com.example.feature_events.presentation.news.viewmodel.SharedNewsFilterViewModel
import javax.inject.Inject

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding
        get() = _binding ?: throw RuntimeException("FragmentNewsBinding == null")

    @Inject
    lateinit var application: Application

    @Inject
    lateinit var newsViewModel: NewsViewModel
    private val sharedNewsFilterViewModel: SharedNewsFilterViewModel by activityViewModels()

    private val component by lazy {
        (requireActivity().application as EventComponentProvider).provideEventComponent()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Log.d("GetViewModel", newsViewModel.toString())
        if (binding.rvNews.adapter == null) {
            Log.d("GetEvents", "onResume")
            observable()
            val session = isFirstEnter(requireContext(), SESSION_EVENTS)
            newsViewModel.getEvents(session)
            binding.rvNews.adapter = PlaceHolderAdapter()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbarNews) {
            filter.setOnClickListener {
                findNavController().navigate(
                    NewsFragmentDirections.actionNewsFragmentToFilterFragment()
                )
            }
        }
    }

    private fun observable() = with(newsViewModel) {

        progressLoader.observe(this@NewsFragment) { loader ->
            if (loader == 100) {
                binding.progressLoader.hide()
            } else {
                binding.progressLoader.progress += loader
                binding.progressLoader.show()
            }
        }

        message.observe(this@NewsFragment) { message ->
            binding.rvNews.adapter = MessageAdapter(message)
        }

        events.observe(this@NewsFragment) { listEvent ->
            listEvent.forEach {
                Log.d("GetEvents", it.title)
            }
            binding.rvNews.adapter.let { adapter ->
                if (adapter is NewsAdapter) {
                    adapter.update(listEvent)
                } else {
                    binding.rvNews.adapter = NewsAdapter(listEvent) { event ->
                        if (!event.isRead) {
                            newsViewModel.updateItemBadge(event)
                        }
                        findNavController().navigate(
                            NewsFragmentDirections.actionNewsFragmentToDetailFragment(event)
                        )
                    }
                }
            }
        }

//        lifecycleScope.launch {
//            countNotReadEventEntity.collect { listCount ->
//                val count = listCount.count { !it.isRead }
//                requireActivity().findViewById<BottomNavigationView>()
//                    .let {
//                        it.getOrCreateBadge(R.id.newsFragment).let { badge ->
//                            badge.number = count
//                            badge.isVisible = count > 0
//                        }
//                    }
//            }
//        }

        sharedNewsFilterViewModel.category.observe(viewLifecycleOwner) { categoryId ->
            newsViewModel.setCategory(categoryId)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}