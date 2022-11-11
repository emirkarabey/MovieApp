package com.emirk.movieapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.emirk.movieapp.data.local.entity.MovieEntity
import com.emirk.movieapp.data.repository.MovieRepositoryImpl
import com.emirk.movieapp.domain.mapper.MovieEntityMapper
import com.emirk.movieapp.domain.use_case.GetWatchLaterMovieUseCase
import com.emirk.movieapp.ui.model.MovieUiModel
import com.emirk.movieapp.utils.DataState
import com.emirk.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: MovieRepositoryImpl,
    private val getWatchLaterMovieUseCase: GetWatchLaterMovieUseCase
) : ViewModel() {

    private val _movies: MutableStateFlow<DataState<List<MovieEntity?>>> =
        MutableStateFlow(DataState.Empty)
    val movies: StateFlow<DataState<List<MovieEntity?>>>
        get() = _movies

    private val _getPopularMovies: MutableStateFlow<Flow<PagingData<MovieUiModel>>> =
        MutableStateFlow(emptyFlow())
    val getPopularMovies: StateFlow<Flow<PagingData<MovieUiModel>>>
        get() = _getPopularMovies

    private val _getTopRatedMovies: MutableStateFlow<Flow<PagingData<MovieUiModel>>> =
        MutableStateFlow(emptyFlow())
    val getTopRatedMovies: StateFlow<Flow<PagingData<MovieUiModel>>>
        get() = _getTopRatedMovies

    private val _getUpComingMovies: MutableStateFlow<Flow<PagingData<MovieUiModel>>> =
        MutableStateFlow(emptyFlow())
    val getUpComingMovies: StateFlow<Flow<PagingData<MovieUiModel>>>
        get() = _getUpComingMovies

    fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _getPopularMovies.value = repository.getPopularMovies(this)
        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _getTopRatedMovies.value = repository.getTopRatedMovies(this)
        }
    }

    fun getUpComingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _getUpComingMovies.value = repository.getUpComingMovies(this)
        }
    }

    suspend fun addWatchLaterMovie(movieUi: MovieUiModel) {
        val movieEntity = MovieEntityMapper().mapToEntity(movieUi)
        repository.addWatchLaterMovie(movieEntity)
    }

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

    fun deleteWatchLaterMovie(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteWatchLaterMovie(movieId)
            getWatchLaterMovie()
        }
    }
}