package com.example.projectjavasimba.presentation.adapter.helperAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectjavasimba.R
import com.example.projectjavasimba.data.entity.Helper
import com.example.projectjavasimba.databinding.ItemHelpSortBinding

class HelperAdapter(
    private val list: List<Helper>
) : RecyclerView.Adapter<HelperViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelperViewHolder {
        context = parent.context
        val binding = ItemHelpSortBinding.inflate(LayoutInflater.from(context), parent, false)
        return HelperViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HelperViewHolder, position: Int) {

        holder.name.text = list[position].name
        Glide.with(context)
            .load(list[position].image)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.image)

    }

    override fun getItemCount(): Int = list.size

}