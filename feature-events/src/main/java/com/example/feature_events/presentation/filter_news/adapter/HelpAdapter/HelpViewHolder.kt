package com.example.feature_events.presentation.filter_news.adapter.HelpAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.feature_events.databinding.ItemHelperBinding

class HelpViewHolder(binding: ItemHelperBinding): RecyclerView.ViewHolder(binding.root) {

    val title = binding.helpTypeTitle
    val switch = binding.checkHelperType
    val divider = binding.divider

}