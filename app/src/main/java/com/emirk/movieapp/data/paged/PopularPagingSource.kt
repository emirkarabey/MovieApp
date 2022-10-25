package com.emirk.movieapp.data.paged

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.emirk.movieapp.data.remote.ApiService
import com.emirk.movieapp.data.remote.model.movie_lists.Movie

class PopularMoviesPagingSource(private val userService: ApiService) :
    PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = userService.getPopularMovies(page)
            LoadResult.Page(
                data = response.movies,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.movies.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

}