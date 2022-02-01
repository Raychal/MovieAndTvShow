package com.raychal.moviesandtvshowsfinal.ui.home.content.tvshow

import com.raychal.moviesandtvshowsfinal.data.source.local.entity.TvShowEntity

interface TvShowCallback {
    fun onItemClicked(data: TvShowEntity)
}