package com.emirk.movieapp.domain.use_case

import com.emirk.movieapp.data.local.entity.MovieEntity
import com.emirk.movieapp.domain.repository.MovieRepository
import com.emirk.movieapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetWatchLaterMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<MovieEntity>>> = flow {
        try {
            emit(Resource.Loading())
            val movies = repository.getWatchLaterMovie()
            emit(Resource.Success(data = movies))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message()))
        }
    }
}