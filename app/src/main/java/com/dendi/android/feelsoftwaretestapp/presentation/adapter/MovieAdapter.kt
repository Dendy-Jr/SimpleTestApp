package com.dendi.android.feelsoftwaretestapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dendi.android.feelsoftwaretestapp.data.video.Video
import com.dendi.android.feelsoftwaretestapp.databinding.MovieItemBinding
import kohii.v1.core.Common
import kohii.v1.exoplayer.Kohii

class MovieAdapter(
    private val kohii: Kohii
) : ListAdapter<Video, MovieAdapter.MovieViewHolder>(Companion) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieItem = getItem(position)
        kohii.setUp(movieItem.videoUrl) {
            tag = "${movieItem.videoUrl}+${position}"
            repeatMode = Common.REPEAT_MODE_ONE
            preload = true
            threshold = 1.0F

        }.bind(holder.playerView)
    }

    inner class MovieViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val playerView = binding.playerContainer
    }

    companion object : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video) =
            oldItem.videoUrl == newItem.videoUrl


        override fun areContentsTheSame(oldItem: Video, newItem: Video) =
            oldItem == newItem
    }
}