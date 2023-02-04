package com.example.projectjavasimba.presentation.FilterFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projectjavasimba.R
import com.example.projectjavasimba.data.entity.Helper
import com.example.projectjavasimba.databinding.FragmentFilterBinding
import com.example.projectjavasimba.presentation.adapter.HelpAdapter.HelpAdapter
import com.example.projectjavasimba.presentation.adapter.helperAdapter.HelperAdapter


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
        val helper = Helper("Дети", R.drawable.invalid_name)
        val helper2 = Helper("Взрослые", R.drawable.invalid_name2)
        val helper3 = Helper("Пожилые", R.drawable.invalid_name3)
        val helper4 = Helper("Животные", R.drawable.invalid_name4)
        val helper5 = Helper("Мероприятия", R.drawable.invalid_name5)

        val adapter = HelperAdapter(listOf(helper, helper2, helper3, helper4, helper5))
        binding.typeHelpRecycler.adapter = adapter
        binding.typeHelpRecycler.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.helpRecycler.adapter =
            HelpAdapter(listOf("Деньгами", "Вещами", "Проф. помощью", "Волонтерством"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}