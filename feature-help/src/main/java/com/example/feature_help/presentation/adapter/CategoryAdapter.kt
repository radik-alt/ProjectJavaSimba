package com.example.feature_help.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.entity.CategoryEntity
import com.example.feature_help.R
import com.example.feature_help.databinding.ItemHelpSortBinding

class CategoryAdapter(
    private val list: List<CategoryEntity>,
    private val selectCategory: (CategoryEntity) -> Unit
) : RecyclerView.Adapter<CategoryViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        context = parent.context
        val binding = ItemHelpSortBinding.inflate(LayoutInflater.from(context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        holder.name.text = list[position].nameEn
        Glide.with(context)
            .load(list[position].image)
            .placeholder(R.drawable.bg_placeholder)
            .error(R.drawable.ic_launcher_foreground)
            .dontAnimate()
            .into(holder.image)

        holder.itemView.setOnClickListener {
            selectCategory.invoke(list[position])
        }
    }

    override fun getItemCount(): Int = list.size



}