package com.example.projectjavasimba.presentation.adapter.NewsAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.projectjavasimba.databinding.ItemNewsBinding

class NewsViewHolder(binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root) {

    val title = binding.title
    val desc = binding.description
    val date = binding.dateEvent
    val image = binding.imageEvent

}