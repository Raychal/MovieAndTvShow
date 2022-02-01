package com.raychal.moviesandtvshowsfinal.di

import com.raychal.moviesandtvshowsfinal.di.home.HomeFragmentBuildersModule
import com.raychal.moviesandtvshowsfinal.di.home.favorite.FavoriteFragmentBuildersModule
import com.raychal.moviesandtvshowsfinal.ui.detail.DetailActivity
import com.raychal.moviesandtvshowsfinal.ui.home.MainActivity
import com.raychal.moviesandtvshowsfinal.ui.home.content.favorite.FavoriteActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [HomeFragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailMoviesActivity(): DetailActivity

    @ContributesAndroidInjector(modules = [FavoriteFragmentBuildersModule::class])
    abstract fun contributeFavoriteActivity() : FavoriteActivity
}