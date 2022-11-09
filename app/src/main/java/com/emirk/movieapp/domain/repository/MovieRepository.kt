package com.emirk.movieapp.domain.repository

import androidx.paging.PagingData
import com.emirk.movieapp.data.local.entity.MovieEntity
import com.emirk.movieapp.data.remote.model.movie_details.MovieDetails
import com.emirk.movieapp.ui.model.MovieUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(coroutineScope: CoroutineScope): Flow<PagingData<MovieUiModel>>
    fun getLatestMovies(coroutineScope: CoroutineScope): Flow<PagingData<MovieUiModel>>
    fun getTopRatedMovies(coroutineScope: CoroutineScope): Flow<PagingData<MovieUiModel>>
    fun getUpComingMovies(coroutineScope: CoroutineScope): Flow<PagingData<MovieUiModel>>
    fun getNowPlayingMovies(coroutineScope: CoroutineScope): Flow<PagingData<MovieUiModel>>
    suspend fun getMovieDetailById(id: Int): MovieDetails

    suspend fun addWatchLaterMovie(movieEntity: MovieEntity)
    fun getWatchLaterMovie(): List<MovieEntity>
}