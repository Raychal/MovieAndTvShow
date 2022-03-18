package com.raychal.moviesandtvshowsfinal.ui.detail

import androidx.lifecycle.ViewModel
import com.raychal.core.domain.model.Movie
import com.raychal.core.domain.usecase.MovieAppUseCase

class DetailViewModel (private val movieAppUseCase: MovieAppUseCase) :
    ViewModel() {

    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) {
        movieAppUseCase.setMovieFavorite(movie, newStatus)
    }
}