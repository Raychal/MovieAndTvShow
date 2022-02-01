package com.raychal.moviesandtvshowsfinal.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tb_favorite_movie")
data class MovieEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: Int = 0,

    @ColumnInfo(name = "movieOverview")
    var overview: String? = null,

    @ColumnInfo(name = "movieOriginalLanguage")
    var originalLanguage: String? = null,

    @ColumnInfo(name = "movieTitle")
    var title: String? = null,

    @ColumnInfo(name = "moviePoster")
    var posterPath: String? = null,

    @ColumnInfo(name = "movieReleaseDate")
    var releaseDate: String? = null,

    @ColumnInfo(name = "moviePopularity")
    var popularity: Double? = null,

    @ColumnInfo(name = "movieVoteAverage")
    var voteAverage: Double? = null,

    @ColumnInfo(name = "movieVoteCount")
    var voteCount: Int? = null,

    @NonNull
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)