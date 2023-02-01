package com.example.projectjavasimba.presentation.adapter.NewsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectjavasimba.databinding.ItemNewsBinding
import java.util.Objects

class NewsAdapter(
    private val list: List<Objects>
) : RecyclerView.Adapter<NewsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 0

    fun update(list: List<Objects>){

    }
}