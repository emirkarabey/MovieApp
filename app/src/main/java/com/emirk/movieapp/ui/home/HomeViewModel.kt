package com.emirk.movieapp.ui.home
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirk.movieapp.data.repository.MovieRepositoryImpl
import com.emirk.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: MovieRepositoryImpl
): ViewModel() {

    val popularMovies = Resource.success(repository.getPopularMovies(viewModelScope))
    val latestMovies = Resource.success(repository.getLatestMovies(viewModelScope))
    val topRatedMovies = Resource.success(repository.getTopRatedMovies(viewModelScope))
    val upComingMovies = Resource.success(repository.getUpComingMovies(viewModelScope))
    val nowPlayingMovies = Resource.success(repository.getNowPlayingMovies(viewModelScope))
}