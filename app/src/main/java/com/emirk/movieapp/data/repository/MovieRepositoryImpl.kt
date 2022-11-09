package com.emirk.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.emirk.movieapp.data.local.WatchLaterDao
import com.emirk.movieapp.data.local.entity.MovieEntity
import com.emirk.movieapp.data.paged.*
import com.emirk.movieapp.data.remote.ApiService
import com.emirk.movieapp.data.remote.model.movie_details.MovieDetails
import com.emirk.movieapp.domain.repository.MovieRepository
import com.emirk.movieapp.ui.model.MovieUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val watchLaterDao: WatchLaterDao
) : MovieRepository {
    override fun getPopularMovies(coroutineScope: CoroutineScope): Flow<PagingData<MovieUiModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { PopularMoviesPagingSource(apiService) }
        ).flow.cachedIn(coroutineScope)
    }

    override fun getLatestMovies(coroutineScope: CoroutineScope): Flow<PagingData<MovieUiModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { LatestMoviesPagingSource(apiService) }
        ).flow.cachedIn(coroutineScope)
    }

    override fun getTopRatedMovies(coroutineScope: CoroutineScope): Flow<PagingData<MovieUiModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { TopRatedPagingSource(apiService) }
        ).flow.cachedIn(coroutineScope)
    }

    override fun getUpComingMovies(coroutineScope: CoroutineScope): Flow<PagingData<MovieUiModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { UpComingPagingSource(apiService) }
        ).flow.cachedIn(coroutineScope)
    }

    override fun getNowPlayingMovies(coroutineScope: CoroutineScope): Flow<PagingData<MovieUiModel>> {
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

    override suspend fun addWatchLaterMovie(movieEntity: MovieEntity) =
        watchLaterDao.addWatchLaterMovie(movieEntity)

    override fun getWatchLaterMovie(): List<MovieEntity> {
        return watchLaterDao.getAllWatchLaterMovie()
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }
}