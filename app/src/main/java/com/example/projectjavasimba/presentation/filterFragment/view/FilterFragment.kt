package com.example.projectjavasimba.presentation.filterFragment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projectjavasimba.R
import com.example.projectjavasimba.data.entity.TypeHelp
import com.example.projectjavasimba.databinding.FragmentFilterBinding
import com.example.projectjavasimba.presentation.adapter.HelpAdapter.HelpAdapter
import com.example.projectjavasimba.presentation.helpFragment.adapter.CategoryAdapter
import com.example.projectjavasimba.presentation.filterFragment.viewmodel.FilterViewModel
import com.example.projectjavasimba.presentation.newsFragment.viewmodel.SharedNewsFilterViewModel


class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding: FragmentFilterBinding
        get() = _binding ?: throw RuntimeException("FragmentFilterBinding == null")

    private val sharedNewsFilterViewModel: SharedNewsFilterViewModel by activityViewModels()
    private val viewModel: FilterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (binding.helpRecycler.adapter == null) {
            setDefaultData()
            observable()
            viewModel.getLoadData()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            toolbarFilter.back.setOnClickListener { findNavController().popBackStack() }
            toolbarFilter.doneChangeCategory.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setDefaultData() {
        val listTypeHelp = ArrayList<TypeHelp>().apply {
            add(TypeHelp(getString(R.string.help_shirt), false))
            add(TypeHelp(getString(R.string.state_hands), false))
            add(TypeHelp(getString(R.string.prof_help), false))
            add(TypeHelp(getString(R.string.help_many), false))
        }
        binding.helpRecycler.adapter =
            HelpAdapter(listTypeHelp) {

            }
    }

    private fun observable() = with(viewModel){
        listCategory.observe(this@FilterFragment) { listCategory ->
            val adapter = CategoryAdapter(listCategory) { category ->
                sharedNewsFilterViewModel.setCategory(category)
                findNavController().popBackStack()
            }
            binding.typeHelpRecycler.adapter = adapter
            binding.typeHelpRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        }

        messageError.observe(this@FilterFragment) { error ->

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}