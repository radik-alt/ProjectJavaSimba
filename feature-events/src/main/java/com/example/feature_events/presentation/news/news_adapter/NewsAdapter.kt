package com.example.feature_events.presentation.news.news_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.entity.EventEntity
import com.example.feature_events.databinding.ItemNewsBinding

class NewsAdapter(
    private var listEventEntity: List<EventEntity>,
    private val callback: (EventEntity) -> Unit
) : RecyclerView.Adapter<NewsViewHolder>() {

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.run {
            title.text = listEventEntity[position].title
            desc.text = listEventEntity[position].description
            date.text = listEventEntity[position].createAt.toString()


            itemView.setOnClickListener {
                callback.invoke(listEventEntity[position])
            }
        }
    }

    override fun getItemCount(): Int = listEventEntity.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    fun update(newListEventEntity: List<EventEntity>) {
        DiffUtil.calculateDiff(DiffUtilsNews(listEventEntity, newListEventEntity)).run {
            listEventEntity = newListEventEntity
            dispatchUpdatesTo(this@NewsAdapter)
        }
    }
}