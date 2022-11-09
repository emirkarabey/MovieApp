package com.emirk.movieapp.di

import com.emirk.movieapp.data.local.WatchLaterDao
import com.emirk.movieapp.data.remote.ApiService
import com.emirk.movieapp.data.repository.MovieRepositoryImpl
import com.emirk.movieapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(api: ApiService, watchLaterDao: WatchLaterDao):
            MovieRepository = MovieRepositoryImpl(api, watchLaterDao)
}