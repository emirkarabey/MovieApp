package com.emirk.movieapp.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirk.movieapp.data.remote.model.movie_details.MovieDetails
import com.emirk.movieapp.data.repository.MovieRepositoryImpl
import com.emirk.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: MovieRepositoryImpl
): ViewModel() {

    var movieDetailLiveData: MutableLiveData<Resource<MovieDetails>> = MutableLiveData()
    var progressBarLiveData: MutableLiveData<Boolean> = MutableLiveData()
    fun getMovieDetailById(id: Int){
        viewModelScope.launch {
            progressBarLiveData.postValue(true)
            movieDetailLiveData.value = repository.getMovieDetailById(id)
        }
    }
}