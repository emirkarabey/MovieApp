package com.emirk.movieapp.ui.adapter

import com.emirk.movieapp.ui.model.MovieUiModel

interface ItemClickListener {
    fun onClickMovie(id: Int)
    fun onClickWatchLaterButton(movieUi: MovieUiModel, position: Int)
}