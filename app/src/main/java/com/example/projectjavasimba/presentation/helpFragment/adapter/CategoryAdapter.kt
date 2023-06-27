package com.example.projectjavasimba.presentation.helpFragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectjavasimba.R
import com.example.projectjavasimba.data.entity.Category
import com.example.projectjavasimba.databinding.ItemHelpSortBinding

class CategoryAdapter(
    private val list: List<Category>,
    private val selectCategory: (Category) -> Unit
) : RecyclerView.Adapter<CategoryViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        context = parent.context
        val binding = ItemHelpSortBinding.inflate(LayoutInflater.from(context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        holder.name.text = list[position].title
        Glide.with(context)
            .load(list[position].image)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            selectCategory.invoke(list[position])
        }
    }

    override fun getItemCount(): Int = list.size



}