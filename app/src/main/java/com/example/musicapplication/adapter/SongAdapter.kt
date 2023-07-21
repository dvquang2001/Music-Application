package com.example.musicapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicapplication.data.Song
import com.example.musicapplication.databinding.ItemSongImageTimeBinding

class SongAdapter(
    private val context: Context,
    private val onItemClicked: (Song) -> Unit,
) : ListAdapter<Song, SongAdapter.SongViewHolder>(DiffCallback) {

    inner class SongViewHolder(private val binding: ItemSongImageTimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song) {
            binding.apply {
                tvSongName.text = song.name
                tvTotalTime.text = song.totalTime
                //Glide.with(context).load(song.image).into(imgSong)
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Song>() {
            override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            ItemSongImageTimeBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
        holder.itemView.setOnClickListener {
            onItemClicked(currentItem)
        }
    }
}