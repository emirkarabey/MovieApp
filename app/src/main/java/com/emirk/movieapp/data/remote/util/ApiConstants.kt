package com.emirk.movieapp.data.remote.util

object ApiConstants {
    const val BASE_URL = "https://api.themoviedb.org/"
    const val API_KEY = "b97d53717df52bc7172c680dbe2142fb"
    private const val BASE_POSTER_PATH =
        "https://image.tmdb.org/t/p/w342" // https://image.tmdb.org/t/p/w342/5hoS3nEkGGXUfmnu39yw1k52JX5.jpg
    private const val BASE_BACKDROP_PATH = "https://image.tmdb.org/t/p/w780"

    @JvmStatic
    fun getPosterPath(posterPath: String?): String {
        return BASE_POSTER_PATH + posterPath
    }

    @JvmStatic
    fun getBackdropPath(backdropPath: String?): String {
        return BASE_BACKDROP_PATH + backdropPath
    }

    object Endpoints {
        const val POPULAR = "3/movie/popular"
        const val LATEST = "3/movie/latest"
        const val TOP_RATED = "3/movie/top_rated"
        const val UPCOMING = "3/movie/upcoming"
        const val NOW_PLAYING = "3/movie/now_playing"
        const val MOVIE_DETAILS = "3/movie/{id}"
    }
}