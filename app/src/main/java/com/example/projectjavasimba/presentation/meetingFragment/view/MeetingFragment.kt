package com.example.projectjavasimba.presentation.meetingFragment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.projectjavasimba.Utils.Utils
import com.example.projectjavasimba.databinding.FragmentMeetingBinding
import com.example.projectjavasimba.presentation.adapter.searchAdapter.SearchAdapter
import com.example.projectjavasimba.presentation.searchFragment.viewmodel.SearchViewPagerViewModel


class MeetingFragment : Fragment() {

    private var _binding: FragmentMeetingBinding? = null
    private val binding: FragmentMeetingBinding
        get() = _binding ?: throw RuntimeException()

    private val searchViewModel: SearchViewPagerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMeetingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val randomText = Utils()
        binding.recyclerSearch.adapter = SearchAdapter(listOf(randomText.randomStringByJavaRandom(),
            randomText.randomStringByJavaRandom(),
            randomText.randomStringByJavaRandom(),
            randomText.randomStringByJavaRandom()))

        searchViewModel.searchMeeting.observe(viewLifecycleOwner) { searchMeeting ->

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}