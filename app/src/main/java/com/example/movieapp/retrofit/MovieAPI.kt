package com.example.movieapp.retrofit

import com.example.movieapp.entity.Movie
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieAPI {
    @GET("t={Title}")
    fun getMovieByTitle(@Path("Title") Title: String): Call<Movie>
}