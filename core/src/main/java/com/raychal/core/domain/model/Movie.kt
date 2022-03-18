package com.raychal.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    var id: Int,
    var overview: String,
    var originalLanguage: String,
    var title: String,
    var posterPath: String,
    var backdropPath: String,
    var releaseDate: String,
    var popularity: Double,
    var voteAverage: Double,
    var voteCount: Int,
    var favorite: Boolean = false,
    var isTvShows: Boolean = false,
): Parcelable