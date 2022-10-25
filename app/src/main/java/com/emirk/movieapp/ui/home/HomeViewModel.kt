package com.emirk.movieapp.ui.home
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.emirk.movieapp.data.paged.PopularMoviesPagingSource
import com.emirk.movieapp.data.remote.ApiService
import com.emirk.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService
): ViewModel() {
    val popularMovies = Resource.success(Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
        PopularMoviesPagingSource(apiService)
    }).flow.cachedIn(viewModelScope))

}