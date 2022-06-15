package com.example.movieapp.retrofit

import com.example.movieapp.entity.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface MovieAPI {
    @Headers(
        "X-RapidAPI-Host"+": "+ "mdblist.p.rapidapi.com",
        "X-RapidAPI-Key"+": "+ "3497bc1b19msh091ee23a5bb80f7p1c4535jsn64f96aa3c01d"
    )
    @GET("?s=jaws/search/title")
    fun getMovieByTitle(): Call<Movie>
}