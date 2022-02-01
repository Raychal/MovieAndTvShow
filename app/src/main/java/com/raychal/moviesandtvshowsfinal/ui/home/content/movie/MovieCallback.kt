package com.raychal.moviesandtvshowsfinal.ui.home.content.movie

import com.raychal.moviesandtvshowsfinal.data.source.local.entity.MovieEntity

interface MovieCallback {
    fun onItemClicked(data: MovieEntity)
}