package com.example.projectjavasimba.presentation.adapter.categoryDetailAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectjavasimba.R
import com.example.projectjavasimba.domain.entity.CategoryDetail
import com.example.projectjavasimba.databinding.ItemDetailCategoryBinding

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
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.image)

    }
}