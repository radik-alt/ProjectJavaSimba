package com.example.projectjavasimba.presentation.newsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.projectjavasimba.R
import com.example.projectjavasimba.data.entity.Event
import com.example.projectjavasimba.databinding.FragmentNewsBinding
import com.example.projectjavasimba.presentation.adapter.NewsAdapter.NewsAdapter
import com.example.projectjavasimba.utils.Constants.getListSaveInstance
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.collections.ArrayList


class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding
        get() = _binding ?: throw RuntimeException("FragmentNewsBinding == null")

    private val sharedNewsFilterViewModel: SharedNewsFilterViewModel by activityViewModels()
    private val newsViewModel: NewsViewModel by viewModels()

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
        getListData()
    }

    private fun getListData() {

        sharedNewsFilterViewModel.getCategory().observe(viewLifecycleOwner) { category ->
            newsViewModel.setCategory(category)
        }

        newsViewModel.progressLoader.observe(viewLifecycleOwner){ loader ->
            if (loader == 100) {
                binding.progressLoader.hide()
            } else {
                binding.progressLoader.progress += loader
                binding.progressLoader.show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val saveList = savedInstanceState?.getParcelableArrayList<Event>(getListSaveInstance)
        if (saveList == null) {
            newsViewModel.getParseListEvent()
        }

        newsViewModel.getListEvent().observe(viewLifecycleOwner) { listEvent ->
            savedInstanceState?.putParcelableArrayList(getListSaveInstance, listEvent as ArrayList<out Event>);
            if (saveList != null) {
                setAdapter(saveList)
            } else {
                setAdapter(listEvent)
            }
        }

        binding.toolbarNews.filter.setOnClickListener {
            val action = NewsFragmentDirections.actionNewsFragment2ToFilterFragment()
            findNavController().navigate(action)
        }
    }

    private fun setAdapter(listEvent: List<Event>) {
        binding.recyclerNews.adapter = NewsAdapter(listEvent) { event ->
            val action = NewsFragmentDirections.actionNewsFragment2ToDetailFragment(event)
            findNavController().navigate(action)
        }
    }

    private fun showBottomNavigation(){
        val fragmentActivity = activity
        if (activity != null){
            val bottom = fragmentActivity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
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