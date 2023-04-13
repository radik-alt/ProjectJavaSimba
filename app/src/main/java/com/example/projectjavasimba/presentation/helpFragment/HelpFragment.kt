package com.example.projectjavasimba.presentation.helpFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projectjavasimba.R
import com.example.projectjavasimba.data.entity.Category
import com.example.projectjavasimba.databinding.FragmentHelpragmentBinding
import com.example.projectjavasimba.presentation.adapter.helperAdapter.HelperAdapter
import com.example.projectjavasimba.common.utils.Constants


class HelpFragment : Fragment() {

    private var _binding: FragmentHelpragmentBinding? = null
    private val binding: FragmentHelpragmentBinding
        get() = _binding ?: throw RuntimeException()

    private val viewModel: HelpViewModel by activityViewModels()
    override fun onResume() {
        super.onResume()
        setToolbar()
        loaderShow()
        viewModel.getParseListCategory()
    }

    private fun loaderShow() {
        viewModel.progressLoader.observe(viewLifecycleOwner) { loader ->
            if (loader == 100) {
                binding.selectCategoryTitle.text = getString(R.string.text_select_sort_help)
                binding.progressLoader.hide()
            } else {
                binding.selectCategoryTitle.text = getString(R.string.load)
                binding.progressLoader.progress += loader
            }
        }
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

        val saveListCategory =
            savedInstanceState?.getParcelableArrayList<Category>(Constants.getListSaveInstanceCategory)

        viewModel.listCategory.observe(viewLifecycleOwner) { listCategory ->
            if (saveListCategory == null)
                setAdapter(listCategory)
            else {
                setAdapter(listCategory)
                savedInstanceState.putParcelableArrayList(
                    Constants.getListSaveInstanceCategory,
                    listCategory as ArrayList<out Category>
                )
            }
        }
    }

    private fun setAdapter(listCategory: List<Category>) {
        val adapter = HelperAdapter(listCategory)
        binding.recyclerHelper.adapter = adapter
        binding.recyclerHelper.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun setToolbar() {
        binding.toolbarHelper.text.text = getString(R.string.help)
        binding.toolbarHelper.create.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}