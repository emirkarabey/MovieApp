package com.emirk.movieapp.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emirk.movieapp.data.remote.util.ApiConstants
import com.emirk.movieapp.databinding.ItemMovieBinding
import com.emirk.movieapp.ui.model.MovieUiModel

class WatchLaterHomeViewHolder(
    private val binding: ItemMovieBinding,
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: MovieUiModel) = binding.apply {
        tvTitle.text = movie.title
        tvReleaseDate.text = movie.release_date
        Glide.with(ivMovie).load(ApiConstants.getPosterPath(movie.poster_path)).into(ivMovie)
        binding.favButton.visibility = View.GONE
    }
}