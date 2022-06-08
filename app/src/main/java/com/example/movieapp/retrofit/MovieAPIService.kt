package com.example.movieapp.retrofit

import com.example.movieapp.entity.Movie
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieAPIService {

    /*
//https://api.themoviedb.org/3/movie/{movie_id}/lists?api_key=<<api_key>>&language=en-US&page=1
    private val BASE_URL ="http://www.omdbapi.com/?&apikey=f4e49519"
    //http://www.omdbapi.com/?&apikey=f4e49519
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MovieAPI::class.java)
    fun getData(): Call<Movie> {
        return api.getMovieByTitle("")
    }


     */





    companion object{
        private var omdbapi: MovieAPI? = null

        fun getData(): MovieAPI?{
            if(omdbapi == null){
                omdbapi = Retrofit.Builder()
                    .baseUrl("http://www.omdbapi.com/?&apikey=eb73867f")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MovieAPI::class.java);

            }

            return omdbapi
        }

    }




}