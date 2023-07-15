package com.example.projectjavasimba.presentation.newsFragment.NewsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.projectjavasimba.data.entity.EventEntity
import com.example.projectjavasimba.databinding.ItemNewsBinding

class NewsAdapter(
    private var listEventEntity: List<EventEntity>,
    private val callback: (EventEntity) -> Unit
) : RecyclerView.Adapter<NewsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        holder.title.text = listEventEntity[position].title
        holder.desc.text = listEventEntity[position].description
        holder.date.text = listEventEntity[position].date


        holder.itemView.setOnClickListener {
            callback.invoke(listEventEntity[position])
        }
    }

    override fun getItemCount(): Int = listEventEntity.size

    fun update(newListEventEntity: List<EventEntity>){
        DiffUtil.calculateDiff(DiffUtilsNews(listEventEntity, newListEventEntity)).run {
            listEventEntity = newListEventEntity
            dispatchUpdatesTo(this@NewsAdapter)
        }
    }
}