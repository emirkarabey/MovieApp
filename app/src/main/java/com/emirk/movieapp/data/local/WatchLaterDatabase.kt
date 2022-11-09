package com.emirk.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emirk.movieapp.data.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class WatchLaterDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): WatchLaterDao
}