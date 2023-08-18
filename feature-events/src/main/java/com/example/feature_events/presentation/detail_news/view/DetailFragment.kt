package com.example.feature_events.presentation.detail_news.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.entity.CategoryDetail
import com.example.feature_events.R
import com.example.feature_events.databinding.FragmentDetailBinding
import com.example.feature_events.presentation.detail_news.adapter.category_detail_adapter.CategoryDetailAdapter
import com.example.feature_events.presentation.detail_news.adapter.friends_detail_adapter.FriendsDetailAdapter
import com.example.feature_events.presentation.detail_news.adapter.image_adapter.ImageDetailAdapter

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
            back.setOnClickListener {
                findNavController().popBackStack()
            }
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
            date.text = event.startDate.toString()
            countFriends.text = "3"
            street.text = event.street
            phone.text = event.phone
            setDefaultAdapter()
        }
    }

    private fun setDefaultAdapter() {
        binding.recyclerImageDetail.adapter = ImageDetailAdapter(args.event.listImage)

        binding.recyclerHelpDetail.adapter = CategoryDetailAdapter(
            listOf(
                CategoryDetail(getString(R.string.help_shirt), R.drawable.shirt),
                CategoryDetail(getString(R.string.state_hands), R.drawable.hands),
                CategoryDetail(getString(R.string.prof_help), R.drawable.tools),
                CategoryDetail(getString(R.string.help_many), R.drawable.coins)
            )
        )
        binding.recyclerHelpDetail.layoutManager =
            GridLayoutManager(requireContext(), 2)

        val listFriends = listOf(1, 2, 3, 4, 5, 6, 7)
        binding.recyclerFriendsDetail.adapter = FriendsDetailAdapter(listFriends.take(3))
        binding.countFriends.text = "+${listFriends.size}"
    }

    private fun hideBottomNavigation() {
//        val fragmentActivity = activity
//        if (activity != null) {
//            val bottom =
//                fragmentActivity?.findViewById<BottomNavigationView>(com.example.main.R.id.bottomNavigationView)
//            if (bottom != null && bottom.visibility == View.VISIBLE) {
//                bottom.visibility = View.GONE
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}