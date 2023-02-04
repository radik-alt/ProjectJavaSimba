package com.example.projectjavasimba.presentation.DetailNewsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectjavasimba.R
import com.example.projectjavasimba.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding:FragmentDetailBinding?=null
    private val binding:FragmentDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}