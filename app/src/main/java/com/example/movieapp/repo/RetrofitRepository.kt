package com.example.movieapp.repo

import androidx.lifecycle.MutableLiveData
import com.example.movieapp.entity.Movie
import com.example.movieapp.retrofit.MovieAPI
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RetrofitRepository @Inject constructor(private val movieAPI: MovieAPI){

/*
    val client = OkHttpClient()

    val request = Request.Builder()
        .url("https://movies-app1.p.rapidapi.com/api/movies")
        .get()
        .addHeader("X-RapidAPI-Key", "3497bc1b19msh091ee23a5bb80f7p1c4535jsn64f96aa3c01d")
        .addHeader("X-RapidAPI-Host", "movies-app1.p.rapidapi.com")
        .build()

    fun getMovie(liveData: MutableLiveData<List<Movie>>) {
        client.newCall(request).enqueue(object :okhttp3.Callback{
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                response.use {
                    if (response.isSuccessful){
                        println("succes")
                        //println(response.headers.get("status"))

                        println(response.header("key1","a"))

                    }else{
                        println("fail")
                    }
                }

            }

        })
    }

 */




    fun getMovie(liveData: MutableLiveData<Movie>){
        val call: Call<Movie> = movieAPI.getMovieByTitle()
        call.enqueue(object :Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful){
                    liveData.postValue(response.body())
                    println("succes")
                    println(response.body()!!.title)
                }else{
                    liveData.postValue(null)
                    println("fail")
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                liveData.postValue(null)
                println("fail")
                println(t.localizedMessage)
            }
        })
    }


}