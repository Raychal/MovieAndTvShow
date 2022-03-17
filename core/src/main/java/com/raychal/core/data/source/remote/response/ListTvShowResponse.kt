package com.raychal.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTvShowResponse(
    @field:SerializedName("results")
    val results: List<TvShowResponse>
)

data class TvShowResponse (

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("original_language")
    val originalLanguage: String,

    @field:SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("original_name")
    val originalName: String,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("tagline")
    val tagline: String,

    @field:SerializedName("vote_count")
    val voteCount: Int,

    @field:SerializedName("status")
    val status: String
)