package com.raychal.moviesandtvshowsfinal.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raychal.moviesandtvshowsfinal.R

fun ImageView.loadFromUrl(path: String) {
    Glide.with(this).clear(this)
    Glide.with(this)
        .setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.poster_a_start_is_born)
                .error(R.drawable.poster_a_start_is_born)
        ).load(path).into(this)
}