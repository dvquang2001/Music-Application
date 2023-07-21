package com.example.musicapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicapplication.data.RecommendationSong
import com.example.musicapplication.databinding.ItemRecommendBinding

class RecommendationSongAdapter(
    private val context: Context,
    private val onItemClicked: (RecommendationSong) -> Unit,
) : ListAdapter<RecommendationSong, RecommendationSongAdapter.RecommendSongViewHolder>(DiffCallback) {

    inner class RecommendSongViewHolder(private val binding: ItemRecommendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(song: RecommendationSong) {
            binding.apply {
                tvTitle.text = song.title
                tvContent.text = song.desc
                //Glide.with(context).load(song.image).into(imgTopic)
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<RecommendationSong>() {
            override fun areItemsTheSame(oldItem: RecommendationSong, newItem: RecommendationSong): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RecommendationSong, newItem: RecommendationSong): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendSongViewHolder {
        return RecommendSongViewHolder(
            ItemRecommendBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: RecommendSongViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
        holder.itemView.setOnClickListener {
            onItemClicked(currentItem)
        }
    }
}