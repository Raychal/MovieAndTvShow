package com.raychal.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieEntities")
data class MovieEntity(

    @ColumnInfo(name = "movieOverview")
    var overview: String? = null,

    @ColumnInfo(name = "movieOriginalLanguage")
    var originalLanguage: String? = null,

    @ColumnInfo(name = "movieReleaseDate")
    var releaseDate: String? = null,

    @ColumnInfo(name = "moviePopularity")
    var popularity: Double? = null,

    @ColumnInfo(name = "movieVoteAverage")
    var voteAverage: Double? = null,

    @ColumnInfo(name = "movieVoteCount")
    var voteCount: Int? = null,

    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "movieTitle")
    var title: String? = null,

    @ColumnInfo(name = "moviePoster")
    var posterPath: String? = null,

    @ColumnInfo(name = "movieBackdropPoster")
    var backdropPath: String? = null,

    @ColumnInfo(name = "isFavorite")
    var favorite: Boolean = false,

    @ColumnInfo(name = "isTvShow")
    var isTvShows: Boolean = false
)