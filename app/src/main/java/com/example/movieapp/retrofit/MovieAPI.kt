package com.example.movieapp.retrofit

import com.example.movieapp.entity.Movie
import io.reactivex.Single
import retrofit2.http.GET

interface MovieAPI {
    @GET("3497bc1b19msh091ee23a5bb80f7p1c4535jsn64f96aa3c01d")
    fun getMovie(): Single<List<Movie>>
}