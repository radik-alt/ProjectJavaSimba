package com.example.projectjavasimba.presentation.searchFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.projectjavasimba.R
import com.example.projectjavasimba.Utils.HingeAnimation
import com.example.projectjavasimba.Utils.StringUtils.meeting_fragment_pager
import com.example.projectjavasimba.databinding.FragmentSearchBinding
import com.example.projectjavasimba.presentation.adapter.searchViewPager.MySearchViewPager
import com.google.android.material.tabs.TabLayoutMediator
import com.jakewharton.rxbinding.widget.RxSearchView
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SearchFragment : Fragment() {

    private var _binding:FragmentSearchBinding?=null
    private val binding:FragmentSearchBinding
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

        RxSearchView.queryTextChanges(binding.svSearch)
            .debounce(500, TimeUnit.MILLISECONDS)
            .filter { it.isNotBlank() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.viewPagerSearch.currentItem = meeting_fragment_pager
                it.toString().lowercase().trim().let { searchString ->
                    searchViewModel.searchMeeting.value = searchString
                }
            }

        binding.viewPagerSearch.adapter = MySearchViewPager(requireActivity())
        TabLayoutMediator(binding.tabLayout, binding.viewPagerSearch){ tab, pos ->
            when (pos){
                0 -> {
                    tab.text = getString(R.string.meeting_title)
                }
                1 -> {
                    tab.text = getString(R.string.nko_title)
                }
            }
        }.attach()
        binding.viewPagerSearch.setPageTransformer(HingeAnimation())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}