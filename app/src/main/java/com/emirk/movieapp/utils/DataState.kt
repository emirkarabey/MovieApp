package com.emirk.movieapp.utils

sealed class DataState<out T> {
    object Loading : DataState<Nothing>()
    object Empty : DataState<Nothing>()
    data class Success<out T>(val data: T) : DataState<T>()
    data class Failure(val error: String?) : DataState<Nothing>()
}