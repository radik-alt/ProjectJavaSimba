package com.example.feature_help.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.feature_help.databinding.ItemHelpSortBinding

class HelperViewHolder(binding: ItemHelpSortBinding) : RecyclerView.ViewHolder(binding.root) {

    val name = binding.textHelper
    val image = binding.imageHelper

}