package com.emirk.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.emirk.movieapp.databinding.ItemMovieBinding
import com.emirk.movieapp.ui.model.MovieUiModel

class MoviesAdapter(private val listener: ItemClickListener) :
    PagingDataAdapter<MovieUiModel, MoviesViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<MovieUiModel>() {
        override fun areItemsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
            return oldItem == newItem
        }
    }

    var watchLaterMoviesId: List<Int> = mutableListOf()

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position)!!, holder.itemView, listener, watchLaterMoviesId,position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }


}