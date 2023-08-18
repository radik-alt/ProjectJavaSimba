package com.example.feature_events.presentation.detail_news.adapter.image_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feature_events.R
import com.example.feature_events.databinding.ItemImageDetailBinding

class ImageDetailAdapter(
    private val listImage: List<String>,
) : RecyclerView.Adapter<ImageDetailViewHolder>(){

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDetailViewHolder {
        context = parent.context
        val binding =
            ItemImageDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageDetailViewHolder(binding)
    }

    override fun getItemCount(): Int = listImage.size

    override fun onBindViewHolder(holder: ImageDetailViewHolder, position: Int) {
        Glide.with(context)
            .load(listImage[position])
            .placeholder(R.drawable.bg_placeholder)
            .error(R.drawable.ic_launcher_background)
            .dontAnimate()
            .into(holder.image)
    }

    override fun getItemViewType(position: Int): Int {
        when (position) {
            0 -> {
                return TYPE_HALF
            }
            1, 2-> {
                return TYPE_QUARTER
            }
        }
        return TYPE_FULL;
    }

    companion object {
        private const val TYPE_FULL = 0
        private const val TYPE_HALF = 1
        private const val TYPE_QUARTER = 2
    }


}