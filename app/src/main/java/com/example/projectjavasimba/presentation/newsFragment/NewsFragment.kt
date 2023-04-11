package com.example.projectjavasimba.presentation.newsFragment

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.projectjavasimba.R
import com.example.projectjavasimba.data.entity.Event
import com.example.projectjavasimba.databinding.FragmentNewsBinding
import com.example.projectjavasimba.presentation.adapter.NewsAdapter.NewsAdapter
import com.example.projectjavasimba.common.utils.Constants.getListSaveInstanceEvent
import com.example.projectjavasimba.service.ServiceGetData
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.collections.ArrayList


class NewsFragment : Fragment(), ServiceGetData.CallbackData {

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
        startService()
    }

    private fun getListData() {

        sharedNewsFilterViewModel.getCategory().observe(viewLifecycleOwner) { category ->
            newsViewModel.setCategory(category)
        }

        newsViewModel.progressLoader.observe(viewLifecycleOwner) { loader ->
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

        val saveList =
            savedInstanceState?.getParcelableArrayList<Event>(getListSaveInstanceEvent)
        if (saveList == null) {
            // вывебри способ загрузки
            val select = 2
            if (select == 1) {
                newsViewModel.getParseListEvent()
            } else {
                startService()
            }
        }

        newsViewModel.getListEvent().observe(viewLifecycleOwner) { listEvent ->

            if (saveList != null) {
                savedInstanceState.putParcelableArrayList(
                    getListSaveInstanceEvent,
                    listEvent as ArrayList<out Event>
                )
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

    private fun startService() {
        val serviceIntent = Intent(requireContext(), ServiceGetData::class.java)
        requireContext().bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun stopService() {
        requireContext().unbindService(serviceConnection)
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as? ServiceGetData.LocalBinder
            binder?.getService().let { service ->
                service?.callback = this@NewsFragment
                service?.getData()
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {}
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDataReceived(list: List<Event>) {
        newsViewModel.setListEvent(list)
    }
}