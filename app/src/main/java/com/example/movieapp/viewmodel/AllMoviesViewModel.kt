package com.example.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.entity.Movie
import com.example.movieapp.repo.RetrofitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AllMoviesViewModel @Inject constructor(private val repository: RetrofitRepository):ViewModel(){

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

    /*
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

     */

    var liveData:MutableLiveData<Movie>
    init {
        liveData = MutableLiveData()
    }
    fun getLiveDataObserver():MutableLiveData<Movie>{
        return liveData
    }
    fun loadData(){
        repository.getMovie(liveData)
    }

}