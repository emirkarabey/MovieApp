package com.emirk.movieapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emirk.movieapp.data.local.entity.MovieEntity

@Dao
interface WatchLaterDao {
    @Insert
    suspend fun addFavoriteMovie(movie: MovieEntity)

    @Query("DELETE FROM favorite_movie WHERE uid=:movieId")
    suspend fun deleteMovie(movieId: Int)

    @Query("SELECT * FROM favorite_movie")
    fun getAllFavoriteMovie(): List<MovieEntity>
}