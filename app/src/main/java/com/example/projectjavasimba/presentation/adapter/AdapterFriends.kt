package com.example.projectjavasimba.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectjavasimba.databinding.ItemFriendsBinding

class AdapterFriends(
    private val listFriends: List<String>
) : RecyclerView.Adapter<ViewHolderFriends>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFriends {
        val binding = ItemFriendsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderFriends(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderFriends, position: Int) {

    }

    override fun getItemCount(): Int = listFriends.size
}