package com.example.projectjavasimba.presentation.helpFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projectjavasimba.R
import com.example.projectjavasimba.data.entity.Helper
import com.example.projectjavasimba.databinding.FragmentHelpragmentBinding
import com.example.projectjavasimba.presentation.adapter.helperAdapter.HelperAdapter


class HelpFragment : Fragment() {

    private var _binding: FragmentHelpragmentBinding?=null
    private val binding:FragmentHelpragmentBinding
        get() = _binding ?: throw RuntimeException()

    override fun onResume() {
        super.onResume()
        setAdapter()
        workView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHelpragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setAdapter(){
        val helper = Helper("Дети", R.drawable.invalid_name)
        val helper2 = Helper("Взрослые", R.drawable.invalid_name2)
        val helper3 = Helper("Пожилые", R.drawable.invalid_name3)
        val helper4 = Helper("Животные", R.drawable.invalid_name4)
        val helper5 = Helper("Мероприятия", R.drawable.invalid_name5)

        val adapter = HelperAdapter(listOf(helper, helper2, helper3, helper4, helper5))
        binding.recyclerHelper.adapter = adapter
        binding.recyclerHelper.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun workView(){
        binding.toolbarHelper.text.text = "Помощь"
        binding.toolbarHelper.create.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}