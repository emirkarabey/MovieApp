package com.example.movieapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "movies")
data class Movie(val movieId:Int,val title:String,val date:String)
