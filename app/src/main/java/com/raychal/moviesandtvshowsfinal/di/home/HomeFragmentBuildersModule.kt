package com.raychal.moviesandtvshowsfinal.di.home

import com.raychal.moviesandtvshowsfinal.ui.home.content.movie.MovieFragment
import com.raychal.moviesandtvshowsfinal.ui.home.content.tvshow.TvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieFragment() : MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeTvShowFragment() : TvShowFragment

}