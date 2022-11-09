package com.emirk.movieapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirk.movieapp.data.repository.MovieRepositoryImpl
import com.emirk.movieapp.domain.mapper.MovieEntityMapper
import com.emirk.movieapp.ui.model.MovieUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: MovieRepositoryImpl
) : ViewModel() {
    val progressBarLiveData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        progressBarLiveData.postValue(false)
    }

    val popularMovies = repository.getPopularMovies(viewModelScope)
    val topRatedMovies = repository.getTopRatedMovies(viewModelScope)
    val upComingMovies = repository.getUpComingMovies(viewModelScope)
    val nowPlayingMovies = repository.getNowPlayingMovies(viewModelScope)

    suspend fun addWatchLaterMovie(movieUi: MovieUiModel) {
        val movieEntity = MovieEntityMapper().mapToEntity(movieUi)
        repository.addWatchLaterMovie(movieEntity)
    }
}