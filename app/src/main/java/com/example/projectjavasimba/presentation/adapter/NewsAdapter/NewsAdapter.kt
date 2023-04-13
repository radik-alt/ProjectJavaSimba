package com.example.projectjavasimba.presentation.adapter.NewsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.projectjavasimba.data.entity.Event
import com.example.projectjavasimba.databinding.ItemNewsBinding

class NewsAdapter(
    private var listEvent: List<Event>,
    private val onClickEvent: (Event) -> Unit
) : RecyclerView.Adapter<NewsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        holder.title.text = listEvent[position].title
        holder.desc.text = listEvent[position].description
        holder.date.text = listEvent[position].date


        holder.itemView.setOnClickListener {
            onClickEvent.invoke(listEvent[position])
        }
    }

    override fun getItemCount(): Int = listEvent.size

    fun update(newListEvent: List<Event>){
        DiffUtil.calculateDiff(DiffUtilsNews(listEvent, newListEvent)).run {
            listEvent = newListEvent
            dispatchUpdatesTo(this@NewsAdapter)
        }

    }
}