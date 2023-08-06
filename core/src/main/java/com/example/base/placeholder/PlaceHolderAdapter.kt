package com.example.base.placeholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.databinding.ItemPlaceholderBinding
import kotlin.random.Random

class PlaceHolderAdapter(
    val size: Int = Random.nextInt(2, 7)
): RecyclerView.Adapter<PlaceHolderAdapter.PlaceHolderViewHolder>() {

    inner class PlaceHolderViewHolder(val binding: ItemPlaceholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun getItemCount(): Int = size

    override fun onBindViewHolder(holder: PlaceHolderViewHolder, position: Int) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolderViewHolder =
        PlaceHolderViewHolder(
            ItemPlaceholderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
}