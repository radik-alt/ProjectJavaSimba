package com.example.search.presentation.searchFragment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.core.utils.HingeAnimation
import com.example.search.presentation.searchFragment.viewmodel.SearchViewPagerViewModel
import com.example.search.R
import com.example.search.databinding.FragmentSearchBinding
import com.example.search.presentation.meetingFragment.view.MeetingFragment
import com.example.search.presentation.nkoFragment.view.NKOFragment
import com.example.search.presentation.searchFragment.adapter.MySearchViewPager
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding ?: throw RuntimeException("")

    private val searchViewModel: SearchViewPagerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            binding.svSearch.queryTextChangeFlow()
                .debounce(400)
                .flowOn(Dispatchers.IO)
                .collect {
                    withContext(Dispatchers.Main) {
                        binding.viewPagerSearch.currentItem = 0
                        it.lowercase().trim().let { searchString ->
                            searchViewModel.searchMeeting.value = searchString
                        }
                    }
                }
        }

        binding.viewPagerSearch.adapter = MySearchViewPager(
            requireActivity(),
            arrayListOf(
                MeetingFragment(),
                NKOFragment()
            )
        )
        TabLayoutMediator(binding.tabLayout, binding.viewPagerSearch) { tab, pos ->
            when (pos) {
                0 -> tab.text = getString(R.string.meeting_title)
                1 -> tab.text = getString(R.string.nko_title)
            }
        }.attach()
        binding.viewPagerSearch.setPageTransformer(HingeAnimation())
    }

    private fun SearchView.queryTextChangeFlow(): Flow<String> = callbackFlow {
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                trySend(newText)
                return true
            }
        })

        awaitClose {
            setOnQueryTextListener(null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}