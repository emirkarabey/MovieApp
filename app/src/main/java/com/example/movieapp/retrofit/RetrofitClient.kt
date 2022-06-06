package com.example.movieapp.retrofit

import com.example.movieapp.entity.Movie
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object{
            private val BASE_URL ="https://utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com/lookup"
            private val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MovieAPI::class.java)
            fun getData(): Single<List<Movie>>{
                return api.getMovie()
            }
        }

}