package com.emirk.movieapp.data.paged

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.emirk.movieapp.data.remote.ApiService
import com.emirk.movieapp.domain.mapper.MovieNetworkMapper
import com.emirk.movieapp.ui.model.MovieUiModel

class LatestMoviesPagingSource(private val apiService: ApiService) :
    PagingSource<Int, MovieUiModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieUiModel> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val movieUiModel =
                MovieNetworkMapper().fromNetworkList(apiService.getPopularMovies(page).movies)
            LoadResult.Page(
                data = movieUiModel,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (movieUiModel.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, MovieUiModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

}