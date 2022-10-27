package com.emirk.movieapp.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.emirk.movieapp.data.remote.model.movie_details.Genre
import com.emirk.movieapp.data.remote.util.ApiConstants

@BindingAdapter("loadImage")
fun ImageView.loadImage(path: String?) {
    if (path.isNullOrEmpty()) return
    Glide.with(this)
        .load(ApiConstants.getPosterPath(path))
        .into(this)
}

@BindingAdapter("loadBackImage")
fun ImageView.loadBackImage(path: String?) {
    if (path.isNullOrEmpty()) return
    Glide.with(this)
        .load(ApiConstants.getBackdropPath(path))
        .into(this)
}

@BindingAdapter("tvToString")
fun TextView.tvToString(popularity: Double?) {
    this.text = popularity.toString()
}

@BindingAdapter("firstCategory")
fun TextView.firstCategory(category: List<Genre>?) {
    if (category != null) {
        this.text = category.first().name
    }
}

@BindingAdapter("secondCategory")
fun TextView.secondCategory(category: List<Genre>?) {
    if (category != null) {
        this.text = category.last().name
    }
}
