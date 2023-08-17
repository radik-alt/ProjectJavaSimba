package com.example.feature_events.presentation.newsFragment.news_adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.core.entity.EventEntity

class DiffUtilsNews(
    private val oldList: List<EventEntity>,
    private val newList: List<EventEntity>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}