package com.example.projectjavasimba.presentation.adapter.NewsAdapter

import androidx.recyclerview.widget.DiffUtil
import java.util.Objects

class DiffUtilsNews(
    private val oldList: List<Objects>,
    private val newList: List<Objects>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }
}