package com.emirk.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.emirk.movieapp.data.paged.*
import com.emirk.movieapp.data.remote.ApiService
import com.emirk.movieapp.data.remote.model.movie_details.MovieDetails
import com.emirk.movieapp.data.remote.model.movie_lists.Movie
import com.emirk.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MovieRepository {
    override fun getPopularMovies(coroutineScope: CoroutineScope): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { PopularMoviesPagingSource(apiService) }
        ).flow.cachedIn(coroutineScope)
    }

    override fun getLatestMovies(coroutineScope: CoroutineScope): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { LatestMoviesPagingSource(apiService) }
        ).flow.cachedIn(coroutineScope)
    }

    override fun getTopRatedMovies(coroutineScope: CoroutineScope): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { TopRatedPagingSource(apiService) }
        ).flow.cachedIn(coroutineScope)
    }

    override fun getUpComingMovies(coroutineScope: CoroutineScope): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { UpComingPagingSource(apiService) }
        ).flow.cachedIn(coroutineScope)
    }

    override fun getNowPlayingMovies(coroutineScope: CoroutineScope): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { NowPlayingPagingSource(apiService) }
        ).flow.cachedIn(coroutineScope)
    }

    override suspend fun getMovieDetailById(id: Int): MovieDetails {
        return apiService.getMovieDetailById(id)
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }
}