package com.example.movieapp.repo

import androidx.lifecycle.MutableLiveData
import com.example.movieapp.entity.Movie
import com.example.movieapp.retrofit.MovieAPI
import com.example.movieapp.retrofit.MovieAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetrofitRepository @Inject constructor(private val movieAPI: MovieAPI){
    fun getMovie(liveData: MutableLiveData<Movie>){
        val call: Call<Movie> = movieAPI.getMovieByTitle("s")
        call.enqueue(object :Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful){
                    liveData.postValue(response.body())
                }else{
                    liveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }
}