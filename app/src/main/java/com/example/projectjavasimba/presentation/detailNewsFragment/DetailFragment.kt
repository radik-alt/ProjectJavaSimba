package com.example.projectjavasimba.presentation.detailNewsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.projectjavasimba.R
import com.example.projectjavasimba.data.entity.CategoryDetail
import com.example.projectjavasimba.databinding.FragmentDetailBinding
import com.example.projectjavasimba.presentation.adapter.categoryDetailAdapter.CategoryDetailAdapter
import com.example.projectjavasimba.presentation.adapter.friendsDetailAdapter.FriendsDetailAdapter
import com.example.projectjavasimba.presentation.adapter.imageDetail.ImageDetailAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailBinding == null")

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.run {
            shareNews.setOnClickListener {  }
            back.setOnClickListener { findNavController().popBackStack() }
        }

        binding.run {
            webSite.setOnClickListener {}
            sendEmail.setOnClickListener {}
        }


    }

    override fun onResume() {
        super.onResume()
        setData()
        hideBottomNavigation()
    }

    private fun setData() {
        val event = args.event
        binding.run {
            title.text = event.title
            description.text = event.description
            date.text = event.date
            countFriends.text = event.listFriends.toString()
            street.text = event.street
            phone.text = event.phone
            setAdapter()
        }
    }

    private fun setAdapter() {
        val listImage = args.event.listImage
        binding.recyclerImageDetail.adapter = ImageDetailAdapter(listImage)
        binding.recyclerImageDetail.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val listCategoryDetail = listOf(
            CategoryDetail(getString(R.string.help_shirt), R.drawable.shirt),
            CategoryDetail(getString(R.string.state_hands), R.drawable.hands),
            CategoryDetail(getString(R.string.prof_help), R.drawable.tools),
            CategoryDetail(getString(R.string.help_many), R.drawable.coins)
        )
        binding.recyclerHelpDetail.adapter = CategoryDetailAdapter(listCategoryDetail)
        binding.recyclerHelpDetail.layoutManager =
            GridLayoutManager(requireContext(), listCategoryDetail.size)

        val listFriends = args.event.listFriends
        binding.recyclerFriendsDetail.adapter = FriendsDetailAdapter(listFriends)
        binding.countFriends.text = "+${listFriends.size}"
    }

    private fun hideBottomNavigation() {
        val fragmentActivity = activity
        if (activity != null) {
            val bottom =
                fragmentActivity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            if (bottom != null && bottom.visibility == View.VISIBLE) {
                bottom.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}