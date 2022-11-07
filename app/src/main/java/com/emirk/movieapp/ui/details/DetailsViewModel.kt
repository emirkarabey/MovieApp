package com.emirk.movieapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirk.movieapp.data.remote.model.movie_details.MovieDetails
import com.emirk.movieapp.domain.use_case.GetMovieByIdUseCase
import com.emirk.movieapp.utils.DataState
import com.emirk.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getMovieByIdUseCase: GetMovieByIdUseCase
) : ViewModel() {

    private val _movies: MutableStateFlow<DataState<MovieDetails?>> =
        MutableStateFlow(DataState.Empty)
    val movies: StateFlow<DataState<MovieDetails?>>
        get() = _movies

    fun getMovies(id: Int) {
        getMovieByIdUseCase(id = id)
            .onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _movies.value = DataState.Loading
                    }
                    is Resource.Success -> {
                        _movies.value = DataState.Success(resource.data)
                    }
                    is Resource.Error -> {
                        _movies.value = DataState.Failure(resource.message)
                    }
                }
            }.launchIn(viewModelScope)
    }
}