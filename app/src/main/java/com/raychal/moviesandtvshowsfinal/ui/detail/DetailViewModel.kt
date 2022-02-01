package com.raychal.moviesandtvshowsfinal.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.raychal.moviesandtvshowsfinal.data.source.CatalogRepository
import com.raychal.moviesandtvshowsfinal.data.source.local.entity.MovieEntity
import com.raychal.moviesandtvshowsfinal.data.source.local.entity.TvShowEntity
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val catalogRepository: CatalogRepository) :
    ViewModel() {

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity> =
        catalogRepository.getMovieDetail(movieId)

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity> =
        catalogRepository.getTvShowDetail(tvShowId)

    fun setFavoriteMovie(movie: MovieEntity){
        catalogRepository.setFavoriteMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity){
        catalogRepository.setFavoriteTvShow(tvShow)
    }

}