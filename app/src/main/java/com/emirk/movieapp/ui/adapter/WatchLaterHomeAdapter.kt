package com.emirk.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.emirk.movieapp.databinding.ItemMovieBinding
import com.emirk.movieapp.ui.model.MovieUiModel

class WatchLaterHomeAdapter : RecyclerView.Adapter<WatchLaterHomeViewHolder>() {

    object DiffCallback : DiffUtil.ItemCallback<MovieUiModel>() {
        override fun areItemsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
            return oldItem == newItem
        }
    }

    private val diffList = AsyncListDiffer(this, DiffCallback)

    var movies: List<MovieUiModel?>
        get() = diffList.currentList
        set(value) = diffList.submitList(value)

    override fun onBindViewHolder(holder: WatchLaterHomeViewHolder, position: Int) {
        movies[position]?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchLaterHomeViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WatchLaterHomeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}