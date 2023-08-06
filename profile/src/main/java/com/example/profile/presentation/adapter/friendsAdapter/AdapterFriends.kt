package com.example.profile.presentation.adapter.friendsAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.entity.Friends
import com.example.profile.R
import com.example.profile.databinding.ItemFriendsBinding

class AdapterFriends(
    private val listFriends: List<Friends>
) : RecyclerView.Adapter<ViewHolderFriends>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFriends {
        context = parent.context
        val binding = ItemFriendsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderFriends(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderFriends, position: Int) {

        holder.name.text = listFriends[position].name
        Glide.with(context)
            .load(listFriends[position].image)
            .placeholder(R.drawable.bg_placeholder)
            .error(R.drawable.ic_launcher_foreground)
            .dontAnimate()
            .into(holder.image)

    }

    override fun getItemCount(): Int = listFriends.size
}