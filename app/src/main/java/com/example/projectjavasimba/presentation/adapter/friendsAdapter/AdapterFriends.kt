package com.example.projectjavasimba.presentation.adapter.friendsAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectjavasimba.R
import com.example.projectjavasimba.databinding.ItemFriendsBinding
import com.example.projectjavasimba.data.entity.Friends

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
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.image)

    }

    override fun getItemCount(): Int = listFriends.size
}