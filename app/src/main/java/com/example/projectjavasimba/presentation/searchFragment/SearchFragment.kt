package com.example.projectjavasimba.presentation.searchFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectjavasimba.R
import com.example.projectjavasimba.databinding.FragmentProfileBinding
import com.example.projectjavasimba.databinding.FragmentSearchBinding
import com.example.projectjavasimba.presentation.adapter.searchViewPager.MySearchViewPager
import com.google.android.material.tabs.TabLayoutMediator

class SearchFragment : Fragment() {

    private var _binding:FragmentSearchBinding?=null
    private val binding:FragmentSearchBinding
        get() = _binding ?: throw RuntimeException("")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPagerSearch.adapter = MySearchViewPager(requireActivity())
        TabLayoutMediator(binding.tabLayout, binding.viewPagerSearch){ tab, pos ->
            when (pos){
                0 -> {
                    tab.text = "По мероприятиям"
                }
                1 -> {
                    tab.text = "По НКО"
                }
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}