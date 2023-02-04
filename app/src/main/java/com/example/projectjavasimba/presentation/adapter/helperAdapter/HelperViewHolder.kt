package com.example.projectjavasimba.presentation.adapter.helperAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.projectjavasimba.databinding.ItemHelpSortBinding

class HelperViewHolder(binding: ItemHelpSortBinding) : RecyclerView.ViewHolder(binding.root) {

    val name = binding.textHelper
    val image = binding.imageHelper

}