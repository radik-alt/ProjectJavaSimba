package com.example.projectjavasimba.presentation.adapter.NewsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.projectjavasimba.data.entity.Event
import com.example.projectjavasimba.databinding.ItemNewsBinding
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject

class NewsAdapter(
    private var listEvent: List<Event>,
    private val onClickEvent: PublishSubject<List<Event>>,
    private val callback: (Event) -> Unit
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
            callback.invoke(listEvent[position])
            checkNews(listEvent[position])
            onClickEvent.onNext(listEvent)
        }
    }

    private fun checkNews(event: Event) {
        event.isRead = true
    }

    override fun getItemCount(): Int = listEvent.size

    fun update(newListEvent: List<Event>){
        DiffUtil.calculateDiff(DiffUtilsNews(listEvent, newListEvent)).run {
            listEvent = newListEvent
            dispatchUpdatesTo(this@NewsAdapter)
        }

    }
}