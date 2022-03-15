package com.raychal.moviesandtvshowsfinal.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse (

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("original_language")
    val originalLanguage: String,

    @field:SerializedName("runtime")
    val runtime: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("revenue")
    val revenue: Long,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("tagline")
    val tagline: String,

    @field:SerializedName("vote_count")
    val voteCount: Int,

    @field:SerializedName("budget")
    val budget: Long,

    @field:SerializedName("status")
    val status: String
)