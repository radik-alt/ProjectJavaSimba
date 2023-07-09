package com.example.projectjavasimba.presentation.adapter.MessageAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectjavasimba.databinding.LayoutErrorMessageBinding

class MessageAdapter(
    private val errorMessage: String
) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    inner class MessageViewHolder(val binding: LayoutErrorMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val message = binding.tvMessage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder =
        MessageViewHolder(
            LayoutErrorMessageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.run {
            message.text = errorMessage
        }
    }

}