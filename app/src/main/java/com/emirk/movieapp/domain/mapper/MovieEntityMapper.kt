package com.emirk.movieapp.domain.mapper

import com.emirk.movieapp.data.local.entity.MovieEntity
import com.emirk.movieapp.ui.model.MovieUiModel

class MovieEntityMapper : EntityMapper<MovieEntity, MovieUiModel> {
    override fun mapFromEntity(entity: MovieEntity): MovieUiModel {
        return MovieUiModel(
            adult = entity.adult,
            backdrop_path = entity.backdrop_path,
            budget = entity.budget,
            homepage = entity.homepage,
            id = entity.id,
            imdb_id = entity.imdb_id,
            original_language = entity.original_language,
            original_title = entity.original_title,
            overview = entity.overview,
            popularity = entity.popularity,
            poster_path = entity.poster_path,
            release_date = entity.release_date,
            revenue = entity.revenue,
            runtime = entity.runtime,
            status = entity.status,
            tagline = entity.tagline,
            title = entity.title,
            video = entity.video,
            vote_average = entity.vote_average,
            vote_count = entity.vote_count,
            genres = null,
            spoken_languages = null,
            production_companies = null,
            isFav = true
        )
    }

    override fun mapToEntity(domainModel: MovieUiModel): MovieEntity {
        return MovieEntity(
            adult = domainModel.adult,
            backdrop_path = domainModel.backdrop_path,
            budget = domainModel.budget,
            homepage = domainModel.homepage,
            id = domainModel.id,
            imdb_id = domainModel.imdb_id,
            original_language = domainModel.original_language,
            original_title = domainModel.original_title,
            overview = domainModel.overview,
            popularity = domainModel.popularity,
            poster_path = domainModel.poster_path,
            release_date = domainModel.release_date,
            revenue = domainModel.revenue,
            runtime = domainModel.runtime,
            status = domainModel.status,
            tagline = domainModel.tagline,
            title = domainModel.title,
            video = domainModel.video,
            vote_average = domainModel.vote_average,
            vote_count = domainModel.vote_count
        )
    }

    fun fromEntityList(initial: List<MovieEntity>): List<MovieUiModel> {
        return initial.map { mapFromEntity(it) }
    }
}