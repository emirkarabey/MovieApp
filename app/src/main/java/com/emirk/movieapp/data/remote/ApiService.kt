package com.emirk.movieapp.data.remote

import com.emirk.movieapp.BuildConfig
import com.emirk.movieapp.data.remote.model.movie_details.MovieDetails
import com.emirk.movieapp.data.remote.model.movie_lists.MovieResponse
import com.emirk.movieapp.data.remote.util.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(ApiConstants.Endpoints.POPULAR)
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): MovieResponse

    @GET(ApiConstants.Endpoints.TOP_RATED)
    suspend fun getTopRatedMovies(
        @Query("page") page: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): MovieResponse

    @GET(ApiConstants.Endpoints.UPCOMING)
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): MovieResponse

    @GET(ApiConstants.Endpoints.MOVIE_DETAILS)
    suspend fun getMovieDetailById(
        @Path("id") id: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): MovieDetails
}