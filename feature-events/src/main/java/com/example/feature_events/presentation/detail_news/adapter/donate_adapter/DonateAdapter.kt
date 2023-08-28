package com.example.feature_events.presentation.detail_news.adapter.donate_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_events.R
import com.example.feature_events.databinding.ItemDonateBinding
import com.example.feature_events.entity.DonateItems

class DonateAdapter(
    private val context: Context,
    private val items: List<DonateItems>,
    private val clickListener: (Int) -> Unit
) : RecyclerView.Adapter<DonateAdapter.DonatViewHolder>() {

    inner class DonatViewHolder(val binding: ItemDonateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val bgItem = binding.llcItem
        val sum = binding.btnItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonatViewHolder =
        DonatViewHolder(
            ItemDonateBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DonatViewHolder, position: Int) {
        holder.run {

            sum.text = items[position].sum.toString()

            if (items[position].select) {
                sum.setTextColor(ContextCompat.getColor(context, R.color.white))
                bgItem.background = context.getDrawable(R.drawable.bg_item_select_donate)
            } else {
                sum.setTextColor(ContextCompat.getColor(context, R.color.green_color))
                bgItem.background = context.getDrawable(R.drawable.bg_item_donate)
            }

            itemView.setOnClickListener {
                updateSelectItem(position)
                clickListener.invoke(items[position].sum)
            }
        }
    }

    private fun updateSelectItem(position: Int) {
        items.forEach { item ->
            if (item.select) {
                item.select = false
                notifyItemChanged(item.id-1)
            }

            if (item.id - 1 == position) {
                item.select = true
                notifyItemChanged(position)
            }
        }
    }

    fun updateAllItems() {
        items.forEach { item ->
            if (item.select) {
                item.select = false
                notifyItemChanged(item.id-1)
            }
        }
    }


}