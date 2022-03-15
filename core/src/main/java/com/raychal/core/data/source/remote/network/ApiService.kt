package com.raychal.moviesandtvshowsfinal.data.source.remote.api

import com.raychal.moviesandtvshowsfinal.BuildConfig
import com.raychal.moviesandtvshowsfinal.data.source.remote.response.ListResponse
import com.raychal.moviesandtvshowsfinal.data.source.remote.response.MovieResponse
import com.raychal.moviesandtvshowsfinal.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = BuildConfig.TOKEN
    ) : Call<ListResponse<MovieResponse>>

    @GET("tv/on_the_air")
    fun getTvShowOnTheAir(
        @Query("api_key") apiKey: String = BuildConfig.TOKEN
    ) : Call<ListResponse<TvShowResponse>>
}