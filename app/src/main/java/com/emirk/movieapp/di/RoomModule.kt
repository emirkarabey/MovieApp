package com.emirk.movieapp.di

import android.content.Context
import androidx.room.Room
import com.emirk.movieapp.data.local.WatchLaterDao
import com.emirk.movieapp.data.local.WatchLaterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val FAV_DATABASE_NAME = "favorite_movie"

@[Module InstallIn(SingletonComponent::class)]
object FavoriteRoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): WatchLaterDatabase {
        return Room.databaseBuilder(context, WatchLaterDatabase::class.java, FAV_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(
        db: WatchLaterDatabase
    ): WatchLaterDao = db.favoriteMovieDao()
}