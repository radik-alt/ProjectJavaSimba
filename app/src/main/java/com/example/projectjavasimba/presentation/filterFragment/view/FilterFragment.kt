package com.example.projectjavasimba.presentation.filterFragment.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projectjavasimba.R
import com.example.common.utils.SESSION_CATEGORY
import com.example.common.utils.isFirstEnter
import com.example.projectjavasimba.domain.entity.TypeHelp
import com.example.projectjavasimba.databinding.FragmentFilterBinding
import com.example.projectjavasimba.di.SimbaApp
import com.example.projectjavasimba.presentation.adapter.HelpAdapter.HelpAdapter
import com.example.projectjavasimba.presentation.adapter.MessageAdapter.MessageAdapter
import com.example.projectjavasimba.presentation.adapter.placeholder.PlaceHolderAdapter
import com.example.projectjavasimba.presentation.helpFragment.adapter.CategoryAdapter
import com.example.projectjavasimba.presentation.filterFragment.viewmodel.FilterViewModel
import com.example.projectjavasimba.presentation.newsFragment.viewmodel.SharedNewsFilterViewModel
import javax.inject.Inject


class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding: FragmentFilterBinding
        get() = _binding ?: throw RuntimeException("FragmentFilterBinding == null")

    private val component by lazy {
        (requireActivity().application as SimbaApp).component
    }

    @Inject
    lateinit var viewModel: FilterViewModel
    private val sharedViewModel: SharedNewsFilterViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

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
            val session = isFirstEnter(requireContext(), SESSION_CATEGORY)
            viewModel.getLoadData(session)
            binding.rvTypeHelp.adapter = PlaceHolderAdapter()
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
        binding.helpRecycler.adapter = HelpAdapter(listTypeHelp) {

        }
    }

    private fun observable() = with(viewModel) {
        listCategory.observe(this@FilterFragment) { listCategory ->
            binding.rvTypeHelp.adapter = CategoryAdapter(listCategory) { category ->
                sharedViewModel.category.value = category.id
                findNavController().popBackStack()
            }
            binding.rvTypeHelp.layoutManager = GridLayoutManager(requireContext(), 2)
        }

        messageError.observe(this@FilterFragment) { message ->
            binding.rvTypeHelp.adapter = MessageAdapter(message)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}