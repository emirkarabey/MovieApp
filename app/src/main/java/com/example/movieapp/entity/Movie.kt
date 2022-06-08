package com.example.movieapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "movies")
data class Movie(@PrimaryKey(autoGenerate = true)
                   @ColumnInfo(name = "movie_id") @NotNull var movie_id:Int,
                 @ColumnInfo(name = "s") @NotNull var movie_name:String,
                 @ColumnInfo(name = "y") @NotNull var movie_date:String
                 //@ColumnInfo(name = "movie_img") @NotNull var movie_img:String
                 ) {
}