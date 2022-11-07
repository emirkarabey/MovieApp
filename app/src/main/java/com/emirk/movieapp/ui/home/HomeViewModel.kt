package com.emirk.movieapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirk.movieapp.data.repository.MovieRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: MovieRepositoryImpl
) : ViewModel() {
    val progressBarLiveData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        progressBarLiveData.postValue(true)
    }

    val popularMovies = repository.getPopularMovies(viewModelScope)
    val topRatedMovies = repository.getTopRatedMovies(viewModelScope)
    val upComingMovies = repository.getUpComingMovies(viewModelScope)
    val nowPlayingMovies = repository.getNowPlayingMovies(viewModelScope)
}