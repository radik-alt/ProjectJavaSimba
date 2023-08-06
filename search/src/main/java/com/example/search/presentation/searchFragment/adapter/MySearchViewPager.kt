package com.example.search.presentation.searchFragment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
class MySearchViewPager(fragmentManager: FragmentActivity, private val listFragment: ArrayList<Fragment>): FragmentStateAdapter(fragmentManager) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> listFragment[0]
            1 -> listFragment[1]
            else -> listFragment[0]
        }
    }

}