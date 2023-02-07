package com.example.projectjavasimba.presentation.newsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.projectjavasimba.data.ParseJSON
import com.example.projectjavasimba.databinding.FragmentNewsBinding
import com.example.projectjavasimba.presentation.adapter.NewsAdapter.NewsAdapter


class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding
        get() = _binding ?: throw RuntimeException("FragmentNewsBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        ParseJSON(requireContext()).parseCategoryJson()
        setAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarNews.filter.setOnClickListener {
            val action = NewsFragmentDirections.actionNewsFragment2ToFilterFragment()
            findNavController().navigate(action)
        }
    }

    private fun setAdapter() {
        binding.recyclerNews.adapter = NewsAdapter(listOf()) { event ->
            val action = NewsFragmentDirections.actionNewsFragment2ToDetailFragment(event)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}