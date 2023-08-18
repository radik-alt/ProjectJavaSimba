package com.example.feature_events.presentation.filter_news.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.feature_events.databinding.ItemHelpSortBinding

class CategoryViewHolder(binding: ItemHelpSortBinding) : RecyclerView.ViewHolder(binding.root) {

    val name = binding.textHelper
    val image = binding.imageHelper

}