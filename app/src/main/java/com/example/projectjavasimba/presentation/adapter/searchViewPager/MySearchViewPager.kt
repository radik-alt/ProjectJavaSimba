package com.example.projectjavasimba.presentation.adapter.searchViewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.projectjavasimba.presentation.meetingFragment.MeetingFragment
import com.example.projectjavasimba.presentation.nkoFragment.NKOFragment

class MySearchViewPager(fragmentManager: FragmentActivity): FragmentStateAdapter(fragmentManager) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> MeetingFragment()
            else -> NKOFragment()
        }
    }

}