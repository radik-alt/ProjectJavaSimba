package com.example.projectjavasimba.presentation.adapter.HelpAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectjavasimba.data.entity.TypeHelp
import com.example.projectjavasimba.databinding.ItemHelperBinding

class HelpAdapter(
    private val listHelp: List<TypeHelp>,
    private val onClickTypeHelp: (TypeHelp) -> Unit,
) : RecyclerView.Adapter<HelpViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpViewHolder {
        val binding = ItemHelperBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HelpViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HelpViewHolder, position: Int) {

        holder.title.text = listHelp[position].name

        holder.itemView.setOnClickListener {
            onClickTypeHelp.invoke(listHelp[position])
        }
    }

    override fun getItemCount(): Int = listHelp.size

}