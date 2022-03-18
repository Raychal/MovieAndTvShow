package com.raychal.moviesandtvshowsfinal.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raychal.core.data.Resource
import com.raychal.core.domain.model.Movie
import com.raychal.core.domain.usecase.MovieAppUseCase

class TvShowsViewModel(private val movieAppUseCase: MovieAppUseCase) : ViewModel() {
    fun getTvShows(sort: String): LiveData<Resource<List<Movie>>> =
        movieAppUseCase.getAllTvShows(sort).asLiveData()
}