package com.example.projectjavasimba.presentation.meetingFragment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.projectjavasimba.databinding.FragmentMeetingBinding
import com.example.projectjavasimba.presentation.meetingFragment.viewmodel.MeetingViewModel
import com.example.projectjavasimba.presentation.searchFragment.adapter.SearchAdapter
import com.example.projectjavasimba.presentation.searchFragment.viewmodel.SearchViewPagerViewModel


class MeetingFragment : Fragment() {

    private var _binding: FragmentMeetingBinding? = null
    private val binding: FragmentMeetingBinding
        get() = _binding ?: throw RuntimeException()

    private val searchViewModel: SearchViewPagerViewModel by activityViewModels()
    private val viewModel: MeetingViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        if (binding.recyclerSearch.adapter == null) {
            observer()
            viewModel.getMeeting()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMeetingBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun observer() = with(viewModel) {
        meeting.observe(this@MeetingFragment) { meeting ->
            binding.recyclerSearch.adapter = SearchAdapter(meeting)
        }

        searchViewModel.searchMeeting.observe(viewLifecycleOwner) { searchMeeting ->
            searchMeeting?.let { it -> getMeetingByFilter(it) }
        }

        errorMessage.observe(this@MeetingFragment) { message ->
//            binding.recyclerSearch.adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}