package com.example.feature_events.presentation.detail_news.adapter.friends_detail_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feature_events.R
import com.example.feature_events.databinding.ItemDetailFriendsBinding

class FriendsDetailAdapter(
    private val listFriends:List<Int>
): RecyclerView.Adapter<FriendsDetailViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsDetailViewHolder {
        context = parent.context
        val binding = ItemDetailFriendsBinding.inflate(LayoutInflater.from(context), parent, false)
        return FriendsDetailViewHolder(binding)
    }

    override fun getItemCount(): Int = if (listFriends.size < 5) listFriends.size else 5

    override fun onBindViewHolder(holder: FriendsDetailViewHolder, position: Int) {
        Glide.with(context)
            .load(listFriends[position])
            .error(R.drawable.ic_launcher_background)
            .into(holder.image)
    }
}