package com.example.feature_events.presentation.filter_fragment.adapter.HelpAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.common.hide
import com.example.core.entity.TypeHelp
import com.example.feature_events.databinding.ItemHelperBinding

class HelpAdapter(
    private val listHelp: List<TypeHelp>,
    private val onClickTypeHelp: (TypeHelp) -> Unit,
) : RecyclerView.Adapter<HelpViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpViewHolder {
        val binding = ItemHelperBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HelpViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HelpViewHolder, position: Int) {

        holder.run {
            title.text = listHelp[position].name

            if (listHelp.size - 1 == position)
                divider.hide()

            itemView.setOnClickListener {
                onClickTypeHelp.invoke(listHelp[position])
            }
        }
    }

    override fun getItemCount(): Int = listHelp.size

}