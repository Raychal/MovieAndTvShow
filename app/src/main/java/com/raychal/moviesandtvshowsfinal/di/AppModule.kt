package com.raychal.moviesandtvshowsfinal.di

import com.raychal.core.domain.usecase.MovieAppInteractor
import com.raychal.core.domain.usecase.MovieAppUseCase
import com.raychal.moviesandtvshowsfinal.ui.detail.DetailViewModel
import com.raychal.moviesandtvshowsfinal.ui.home.MainViewModel
import com.raychal.moviesandtvshowsfinal.ui.movies.MoviesViewModel
import com.raychal.moviesandtvshowsfinal.ui.tvshows.TvShowsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieAppUseCase> { MovieAppInteractor(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { TvShowsViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { MainViewModel(get()) }
}