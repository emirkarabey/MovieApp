package com.emirk.movieapp.domain.repository

import androidx.paging.PagingData
import com.emirk.movieapp.data.remote.model.movie_details.MovieDetails
import com.emirk.movieapp.data.remote.model.movie_lists.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(coroutineScope: CoroutineScope): Flow<PagingData<Movie>>
    fun getLatestMovies(coroutineScope: CoroutineScope): Flow<PagingData<Movie>>
    fun getTopRatedMovies(coroutineScope: CoroutineScope): Flow<PagingData<Movie>>
    fun getUpComingMovies(coroutineScope: CoroutineScope): Flow<PagingData<Movie>>
    fun getNowPlayingMovies(coroutineScope: CoroutineScope): Flow<PagingData<Movie>>
    suspend fun getMovieDetailById(id: Int): MovieDetails
}