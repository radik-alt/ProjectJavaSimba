package com.example.projectjavasimba.presentation.helpFragment.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.projectjavasimba.databinding.ItemHelpSortBinding

class CategoryViewHolder(binding: ItemHelpSortBinding) : RecyclerView.ViewHolder(binding.root) {

    val name = binding.textHelper
    val image = binding.imageHelper

}