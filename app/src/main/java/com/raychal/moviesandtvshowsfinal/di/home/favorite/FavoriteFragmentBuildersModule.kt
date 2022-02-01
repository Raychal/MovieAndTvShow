package com.raychal.moviesandtvshowsfinal.di.home.favorite

import com.raychal.moviesandtvshowsfinal.ui.home.content.favorite.movie.FavoriteMovieFragment
import com.raychal.moviesandtvshowsfinal.ui.home.content.favorite.tvshow.FavoriteTvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoriteFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeFavoriteMovieFragment() : FavoriteMovieFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteTvShowFragment() : FavoriteTvShowFragment
}