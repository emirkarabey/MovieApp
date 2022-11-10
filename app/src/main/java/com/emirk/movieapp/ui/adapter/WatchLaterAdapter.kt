package com.emirk.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.emirk.movieapp.databinding.ItemWatchLaterMovieBinding
import com.emirk.movieapp.ui.model.MovieUiModel

class WatchLaterAdapter(private val listener: WatchLaterClickListener) :
    RecyclerView.Adapter<WatchLaterViewHolder>() {

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

    override fun onBindViewHolder(holder: WatchLaterViewHolder, position: Int) {
        movies[position]?.let { holder.bind(it, listener, position) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchLaterViewHolder {
        val binding =
            ItemWatchLaterMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WatchLaterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}