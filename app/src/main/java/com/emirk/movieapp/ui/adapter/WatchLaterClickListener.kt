package com.emirk.movieapp.ui.adapter

import com.emirk.movieapp.ui.model.MovieUiModel

interface WatchLaterClickListener {
    fun onClickWatchLaterButton(movieUi: MovieUiModel, position: Int)
}