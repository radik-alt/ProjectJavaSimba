package com.example.feature_events.presentation.newsFragment.news_adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.feature_events.databinding.ItemNewsBinding

class NewsViewHolder(binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root) {

    val title = binding.title
    val desc = binding.description
    val date = binding.dateEvent
    val image = binding.imageEvent

}