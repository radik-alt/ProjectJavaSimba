    package com.example.projectjavasimba.presentation.profileFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectjavasimba.R
import com.example.projectjavasimba.databinding.FragmentProfileBinding
import com.example.projectjavasimba.presentation.adapter.friendsAdapter.AdapterFriends
import com.example.projectjavasimba.presentation.entity.Friends


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding?=null
    private val binding:FragmentProfileBinding
        get() = _binding ?: throw RuntimeException("FragmentProfileBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val friends = Friends(null, "Дмитрий Валерьевич", R.drawable.avatar_3)
        val friends2 = Friends(null, "Евгений Александров", R.drawable.avatar_1)
        val friends3 = Friends(null, "Виктор Кузнецов", R.drawable.avatar_2)

        binding.contentProfile.recyclerFriends.adapter = AdapterFriends(listOf(friends, friends2, friends3))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}