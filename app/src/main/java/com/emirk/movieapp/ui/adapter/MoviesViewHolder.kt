package com.emirk.movieapp.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emirk.movieapp.R
import com.emirk.movieapp.data.remote.util.ApiConstants
import com.emirk.movieapp.databinding.ItemMovieBinding
import com.emirk.movieapp.ui.model.MovieUiModel

class MoviesViewHolder(
    private val binding: ItemMovieBinding,
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        movie: MovieUiModel,
        itemView: View,
        listener: ItemClickListener,
        watchLaterMoviesId: List<Int>
    ) = binding.apply {
        tvTitle.text = movie.title
        tvReleaseDate.text = movie.release_date
        watchLaterMoviesId.forEach {
            if (it == movie.id) {
                println(movie.title)
                movie.isFav = true
            }
        }
        Glide.with(ivMovie).load(ApiConstants.getPosterPath(movie.poster_path)).into(ivMovie)

        if (movie.isFav == true) {
            binding.favButton.setBackgroundResource(R.drawable.ic_baseline_bookmark_added_24)
        }else{
            binding.favButton.setBackgroundResource(R.drawable.ic_baseline_bookmark_add_24)
        }

        itemView.setOnClickListener {
            movie.id?.let { it1 -> listener.onClickMovie(it1) }
        }

        binding.favButton.setOnClickListener {
            movie.isFav = true
            bindingAdapter?.notifyDataSetChanged()
            listener.onClickWatchLaterButton(movie)
        }
    }
}