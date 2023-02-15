package com.example.projectjavasimba.presentation.newsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.projectjavasimba.R
import com.example.projectjavasimba.data.ParseJSON
import com.example.projectjavasimba.data.entity.Event
import com.example.projectjavasimba.databinding.FragmentNewsBinding
import com.example.projectjavasimba.presentation.adapter.NewsAdapter.NewsAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView


class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding
        get() = _binding ?: throw RuntimeException("FragmentNewsBinding == null")

    private val sharedNewsFilterViewModel: SharedNewsFilterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        showBottomNavigation()
        var listEvent = ParseJSON(requireContext()).parseEventJson()
        sharedNewsFilterViewModel.getCategory().observe(viewLifecycleOwner) { category ->
            listEvent = listEvent.filter { listEvent -> listEvent.category.contains(category) }
        }
        setAdapter(listEvent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarNews.filter.setOnClickListener {
            val action = NewsFragmentDirections.actionNewsFragment2ToFilterFragment()
            findNavController().navigate(action)
        }
    }

    private fun setAdapter(listEvent: List<Event>) {
        binding.recyclerNews.adapter.let { adapter ->
            if (adapter is NewsAdapter) {
                adapter.update(listEvent)
            } else {
                binding.recyclerNews.adapter = NewsAdapter(listEvent) { event ->
                    val action = NewsFragmentDirections.actionNewsFragment2ToDetailFragment(event)
                    findNavController().navigate(action)
                }
            }
        }

    }

    private fun showBottomNavigation() {
        val fragmentActivity = activity
        if (activity != null) {
            val bottom =
                fragmentActivity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            if (bottom != null && bottom.visibility == View.GONE) {
                bottom.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}