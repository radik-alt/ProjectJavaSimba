package com.example.projectjavasimba.presentation.profileFragment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.projectjavasimba.R
import com.example.projectjavasimba.databinding.FragmentProfileBinding
import com.example.projectjavasimba.presentation.adapter.friendsAdapter.AdapterFriends
import com.example.projectjavasimba.domain.entity.Friends
import com.example.projectjavasimba.presentation.profileDialog.view.DialogFragmentSelect
import com.example.projectjavasimba.presentation.profileFragment.viewmodel.SharedViewModel


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: throw RuntimeException("FragmentProfileBinding == null")

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        sharedViewModel.getImage().observe(viewLifecycleOwner) { uriImage ->
            if (uriImage != null) {
                Glide.with(requireContext())
                    .load(uriImage)
                    .placeholder(R.drawable.bg_placeholder)
                    .error(R.drawable.ic_launcher_foreground)
                    .dontAnimate()
                    .into(binding.imageProfile)
            }
        }

        sharedViewModel.getIsDeleteImage().observe(viewLifecycleOwner) { isDelete ->
            if (isDelete) {
                Glide.with(requireContext())
                    .load(R.drawable.ic_launcher_foreground)
                    .into(binding.imageProfile)
                sharedViewModel.setIsDeleteImage(false)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()

        binding.imageProfile.setOnClickListener {
            val dialogImage = DialogFragmentSelect()
            dialogImage.show(parentFragmentManager, dialogImage.tag)
        }
    }

    private fun setAdapter() {
        val friends = Friends(null, "Дмитрий Валерьевич", R.drawable.avatar_3)
        val friends2 = Friends(null, "Евгений Александров", R.drawable.avatar_1)
        val friends3 = Friends(null, "Виктор Кузнецов", R.drawable.avatar_2)

        binding.contentProfile.recyclerFriends.adapter =
            AdapterFriends(listOf(friends, friends2, friends3))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}