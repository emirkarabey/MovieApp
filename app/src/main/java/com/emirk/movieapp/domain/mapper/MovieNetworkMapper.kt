package com.emirk.movieapp.domain.mapper

import com.emirk.movieapp.data.remote.model.movie_lists.Movie
import com.emirk.movieapp.ui.model.MovieUiModel

class MovieNetworkMapper : EntityMapper<Movie, MovieUiModel> {
    override fun mapFromEntity(entity: Movie): MovieUiModel {
        return MovieUiModel(
            adult = entity.adult,
            backdrop_path = entity.backdrop_path,
            budget = entity.budget,
            genres = entity.genres,
            homepage = entity.homepage,
            id = entity.id,
            imdb_id = entity.imdb_id,
            original_language = entity.original_language,
            original_title = entity.original_title,
            overview = entity.overview,
            popularity = entity.popularity,
            poster_path = entity.poster_path,
            production_companies = entity.production_companies,
            release_date = entity.release_date,
            revenue = entity.revenue,
            runtime = entity.runtime,
            spoken_languages = entity.spoken_languages,
            status = entity.status,
            tagline = entity.tagline,
            title = entity.title,
            video = entity.video,
            vote_average = entity.vote_average,
            vote_count = entity.vote_count,
            isFav = false
        )
    }

    override fun mapToEntity(domainModel: MovieUiModel): Movie {
        return Movie(
            adult = domainModel.adult!!,
            backdrop_path = domainModel.backdrop_path!!,
            budget = domainModel.budget!!,
            genres = domainModel.genres!!,
            homepage = domainModel.homepage!!,
            id = domainModel.id!!,
            imdb_id = domainModel.imdb_id!!,
            original_language = domainModel.original_language!!,
            original_title = domainModel.original_title!!,
            overview = domainModel.overview!!,
            popularity = domainModel.popularity!!,
            poster_path = domainModel.poster_path!!,
            production_companies = domainModel.production_companies!!,
            release_date = domainModel.release_date!!,
            revenue = domainModel.revenue!!,
            runtime = domainModel.runtime!!,
            spoken_languages = domainModel.spoken_languages!!,
            status = domainModel.status!!,
            tagline = domainModel.tagline!!,
            title = domainModel.title!!,
            video = domainModel.video!!,
            vote_average = domainModel.vote_average!!,
            vote_count = domainModel.vote_count!!
        )
    }

    fun fromNetworkList(initial: List<Movie>): List<MovieUiModel> {
        return initial.map { mapFromEntity(it) }
    }
}