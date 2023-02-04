package com.example.projectjavasimba.presentation.FilterFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectjavasimba.R
import com.example.projectjavasimba.databinding.FragmentFilterBinding
import com.example.projectjavasimba.presentation.adapter.HelpAdapter.HelpAdapter


class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding: FragmentFilterBinding
        get() = _binding ?: throw RuntimeException("FragmentFilterBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setAdapter()
    }

    private fun setAdapter() {
        binding.helpRecycler.adapter =
            HelpAdapter(listOf("Деньгами", "Вещами", "Проф. помощью", "Волонтерством"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}