package com.example.movieapp.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.entity.Movie
import com.example.movieapp.retrofit.MovieAPI
import com.example.movieapp.retrofit.MovieAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class AllMoviesViewModel :ViewModel(){
    /*
    val movies = MutableLiveData<List<Movie>>()
    val movieApiService = MovieAPIService()
    val disposable = CompositeDisposable()

    fun getData(){
        disposable.add(
            movieApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Movie>>(){
                    override fun onSuccess(t: List<Movie>) {
                        println("başarılı")
                    }
                    override fun onError(e: Throwable) {
                        println(e.localizedMessage)
                    }
                })
        )
    }

     */

    var omdbapiClient :MovieAPI = MovieAPIService.getData()!!

    fun searchMovieByTitle(movietitle: String) {
        omdbapiClient.getMovieByTitle(movietitle).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>?, response: Response<Movie>) {
                println("success")
            }

            override fun onFailure(call: Call<Movie>?, t: Throwable?) {
                println("fail")
            }

        })
    }
}