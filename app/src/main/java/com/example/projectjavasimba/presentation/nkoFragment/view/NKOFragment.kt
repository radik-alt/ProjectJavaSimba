package com.example.projectjavasimba.presentation.nkoFragment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectjavasimba.Utils.Utils
import com.example.projectjavasimba.databinding.FragmentNKOBinding
import com.example.projectjavasimba.presentation.searchFragment.adapter.SearchAdapter


class NKOFragment : Fragment() {

    private var _binding:FragmentNKOBinding?=null
    private val binding:FragmentNKOBinding
        get() = _binding ?: throw RuntimeException()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        _binding = FragmentNKOBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val randomText = Utils()
        binding.recyclerSearch.adapter = SearchAdapter(listOf(randomText.randomStringByJavaRandom(),
            randomText.randomStringByJavaRandom(),
            randomText.randomStringByJavaRandom(),
            randomText.randomStringByJavaRandom()))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}