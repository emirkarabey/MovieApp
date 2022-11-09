package com.emirk.movieapp.ui.model
import com.emirk.movieapp.data.remote.model.movie_details.Genre
import com.emirk.movieapp.data.remote.model.movie_details.ProductionCompany
import com.emirk.movieapp.data.remote.model.movie_details.SpokenLanguage

data class MovieUiModel (
    val uid: Int=0,
    val adult: Boolean?,
    val backdrop_path: String?,
    val budget: Int?,
    val genres: List<Genre>?,
    val homepage: String?,
    val id: Int?,
    val imdb_id: String?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val production_companies: List<ProductionCompany>?,
    val release_date: String?,
    val revenue: Int?,
    val runtime: Int?,
    val spoken_languages: List<SpokenLanguage>?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?,
    var isFav: Boolean?
        )