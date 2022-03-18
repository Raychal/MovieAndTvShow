package com.raychal.core.utils

import com.raychal.core.data.source.local.entity.MovieEntity
import com.raychal.core.data.source.remote.response.MovieResponse
import com.raychal.core.data.source.remote.response.TvShowResponse
import com.raychal.core.domain.model.Movie

object DataMapper {
    fun mapMovieResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                it.overview,
                it.originalLanguage,
                it.title,
                it.popularity,
                it.voteAverage,
                it.voteCount,
                it.id,
                it.posterPath,
                it.backdropPath,
                it.releaseDate,
                favorite = false,
                isTvShows = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapTvShowResponsesToEntities(input: List<TvShowResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                it.overview,
                it.originalLanguage,
                it.firstAirDate,
                it.popularity,
                it.voteAverage,
                it.voteCount,
                it.id,
                it.posterPath,
                it.backdropPath,
                it.originalName,
                favorite = false,
                isTvShows = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> {
        return input.map {
            Movie(
                it.id!!,
                it.overview!!,
                it.originalLanguage!!,
                it.title!!,
                it.posterPath!!,
                it.backdropPath!!,
                it.releaseDate!!,
                it.popularity!!,
                it.voteAverage!!,
                it.voteCount!!,
                isTvShows = false,
                favorite = false


            )
        }
    }

    fun mapDomainToEntity(input: Movie): MovieEntity {
        return MovieEntity(
            input.overview,
            input.originalLanguage,
            input.releaseDate,
            input.popularity,
            input.voteAverage,
            input.id,
            input.id,
            input.title,
            input.posterPath,
            input.backdropPath,
            favorite = input.favorite,
            isTvShows = input.isTvShows
        )
    }
}