package com.raychal.core.data.source.local

import com.raychal.core.data.source.local.entity.MovieEntity
import com.raychal.core.data.source.local.room.CatalogDao
import com.raychal.core.utils.SortUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class LocalDataSource (private val catalogDao: CatalogDao) {

    fun getAllMovies(sort: String): Flow<List<MovieEntity>> {
        val query = SortUtils.getSortedQueryMovies(sort)
        return catalogDao.getMovies(query)
    }

    fun getAllTvShows(sort: String): Flow<List<MovieEntity>> {
        val query = SortUtils.getSortedQueryTvShows(sort)
        return catalogDao.getTvShows(query)
    }

    fun getAllFavoriteMovies(sort: String): Flow<List<MovieEntity>> {
        val query = SortUtils.getSortedQueryFavoriteMovies(sort)
        return catalogDao.getFavoriteMovies(query)
    }

    fun getAllFavoriteTvShows(sort: String): Flow<List<MovieEntity>> {
        val query = SortUtils.getSortedQueryFavoriteTvShows(sort)
        return catalogDao.getFavoriteTvShows(query)
    }

    fun getMovieSearch(search: String): Flow<List<MovieEntity>> {
        return catalogDao.getSearchMovies(search)
            .flowOn(Dispatchers.Default)
            .conflate()
    }

    fun getTvShowSearch(search: String): Flow<List<MovieEntity>> {
        return catalogDao.getSearchTvShows(search)
            .flowOn(Dispatchers.Default)
            .conflate()
    }

    suspend fun insertMovies(movies: List<MovieEntity>) = catalogDao.insertMovie(movies)

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.favorite = newState
        catalogDao.updateFavoriteMovie(movie)
    }
}