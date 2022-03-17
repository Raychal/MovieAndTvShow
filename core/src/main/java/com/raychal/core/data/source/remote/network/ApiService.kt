package com.raychal.core.data.source.remote.network

import com.raychal.core.data.source.remote.response.ListMovieResponse
import com.raychal.core.data.source.remote.response.ListTvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
    ) : ListMovieResponse

    @GET("tv/on_the_air")
    suspend fun getTvShows(
        @Query("api_key") apiKey: String,
    ) : ListTvShowResponse
}