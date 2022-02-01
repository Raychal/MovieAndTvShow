package com.raychal.moviesandtvshowsfinal.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.raychal.moviesandtvshowsfinal.data.source.CatalogRepository
import com.raychal.moviesandtvshowsfinal.data.source.local.entity.MovieEntity
import com.raychal.moviesandtvshowsfinal.data.source.local.entity.TvShowEntity
import com.raychal.moviesandtvshowsfinal.vo.Resource
import javax.inject.Inject

class MainViewModel @Inject constructor(private val catalogRepository: CatalogRepository) : ViewModel(){

    fun getListNowPlayingMovies(): LiveData<Resource<PagedList<MovieEntity>>> = catalogRepository.getNowPlayingMovies()

    fun getListOnTheAirTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> = catalogRepository.getTvShowOnTheAir()

}