package com.example.fil.presentation.adapter.HelpAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.projectjavasimba.databinding.ItemHelperBinding

class HelpViewHolder(binding: ItemHelperBinding): RecyclerView.ViewHolder(binding.root) {

    val title = binding.helpTypeTitle
    val switch = binding.checkHelperType
    val divider = binding.divider

}