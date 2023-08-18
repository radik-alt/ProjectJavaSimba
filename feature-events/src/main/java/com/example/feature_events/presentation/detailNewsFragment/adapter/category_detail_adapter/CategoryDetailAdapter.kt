package com.example.feature_events.presentation.detailNewsFragment.adapter.category_detail_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.entity.CategoryDetail
import com.example.feature_events.R
import com.example.feature_events.databinding.ItemDetailCategoryBinding

class CategoryDetailAdapter(
    private val list: List<CategoryDetail>,
) : RecyclerView.Adapter<CategoryDetailViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDetailViewHolder {
        context = parent.context
        val binding = ItemDetailCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return CategoryDetailViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CategoryDetailViewHolder, position: Int) {

        holder.title.text = list[position].title

        Glide.with(context)
            .load(list[position].image)
            .placeholder(R.drawable.bg_placeholder)
            .error(R.drawable.ic_launcher_background)
            .dontAnimate()
            .into(holder.image)

    }
}