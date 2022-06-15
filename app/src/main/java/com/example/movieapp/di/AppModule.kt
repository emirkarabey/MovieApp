package com.example.movieapp.di

import com.example.movieapp.retrofit.MovieAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun getMovieAPIInstance(retrofit: Retrofit):MovieAPI{
        return  retrofit.create(MovieAPI::class.java)
    }

    @Singleton
    @Provides
    fun getMovieAPIIns():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://mdblist.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}