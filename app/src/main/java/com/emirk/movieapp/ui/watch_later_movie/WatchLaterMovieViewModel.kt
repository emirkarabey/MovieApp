package com.emirk.movieapp.ui.watch_later_movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirk.movieapp.data.local.entity.MovieEntity
import com.emirk.movieapp.domain.use_case.GetWatchLaterMovieUseCase
import com.emirk.movieapp.utils.DataState
import com.emirk.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchLaterMovieViewModel @Inject constructor(
    private val getWatchLaterMovieUseCase: GetWatchLaterMovieUseCase
) : ViewModel() {

    private val _movies: MutableStateFlow<DataState<List<MovieEntity?>>> =
        MutableStateFlow(DataState.Empty)
    val movies: StateFlow<DataState<List<MovieEntity?>>>
        get() = _movies

    fun getWatchLaterMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            getWatchLaterMovieUseCase.invoke()
                .onEach { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            _movies.value = DataState.Loading
                        }
                        is Resource.Success -> {
                            _movies.value = DataState.Success(resource.data!!)
                        }
                        is Resource.Error -> {
                            _movies.value = DataState.Failure(resource.message)
                        }
                    }
                }.launchIn(this)
        }
    }
}