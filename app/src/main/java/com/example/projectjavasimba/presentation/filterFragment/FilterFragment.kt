package com.example.projectjavasimba.presentation.filterFragment

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
import com.example.projectjavasimba.data.ParseJSON
import com.example.projectjavasimba.data.entity.CategoryDetail
import com.example.projectjavasimba.data.entity.TypeHelp
import com.example.projectjavasimba.databinding.FragmentFilterBinding
import com.example.projectjavasimba.presentation.adapter.HelpAdapter.HelpAdapter
import com.example.projectjavasimba.presentation.adapter.helperAdapter.CategoryAdapter
import com.example.projectjavasimba.presentation.newsFragment.SharedNewsFilterViewModel


class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding: FragmentFilterBinding
        get() = _binding ?: throw RuntimeException("FragmentFilterBinding == null")

    private val sharedNewsFilterViewModel: SharedNewsFilterViewModel by activityViewModels()
    private val filterViewModel: FilterViewModel by viewModels()

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
        filterViewModel.getListCategory()
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

    private fun setAdapter() {
        filterViewModel.listCategory.observe(viewLifecycleOwner) {listCategory ->
            val adapter = CategoryAdapter(listCategory) { category ->
                sharedNewsFilterViewModel.setCategory(category)
                findNavController().popBackStack()
            }
            binding.typeHelpRecycler.adapter = adapter
            binding.typeHelpRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        }


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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}