package com.raychal.moviesandtvshowsfinal.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raychal.core.data.Resource
import com.raychal.core.domain.model.Movie
import com.raychal.core.domain.usecase.MovieAppUseCase

class MoviesViewModel(private val movieAppUseCase: MovieAppUseCase) : ViewModel() {
    fun getMovies(sort: String): LiveData<Resource<List<Movie>>> =
        movieAppUseCase.getAllMovies(sort).asLiveData()
}