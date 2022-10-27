package com.emirk.movieapp.data.repository

import androidx.paging.PagingData
import com.emirk.movieapp.data.remote.model.movie_details.MovieDetails
import com.emirk.movieapp.data.remote.model.movie_lists.Movie
import com.emirk.movieapp.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(coroutineScope: CoroutineScope): Resource<Flow<PagingData<Movie>>>
    fun getLatestMovies(coroutineScope: CoroutineScope): Resource<Flow<PagingData<Movie>>>
    fun getTopRatedMovies(coroutineScope: CoroutineScope): Resource<Flow<PagingData<Movie>>>
    fun getUpComingMovies(coroutineScope: CoroutineScope): Resource<Flow<PagingData<Movie>>>
    fun getNowPlayingMovies(coroutineScope: CoroutineScope): Resource<Flow<PagingData<Movie>>>
    suspend fun getMovieDetailById(id: Int): Resource<MovieDetails>
}