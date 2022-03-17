package com.raychal.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tb_favorite_tvshow")
data class TvShowEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @NonNull
    @ColumnInfo(name = "tvShowId")
    var tvShowId: Int = 0,

    @ColumnInfo(name = "tvShowFirstAirDate")
    var firstAirDate: String? = null,

    @ColumnInfo(name = "tvShowOverview")
    var overview: String? = null,

    @ColumnInfo(name = "tvShowOriginalLanguage")
    var originalLanguage: String? = null,

    @ColumnInfo(name = "tvShowPosterPath")
    val posterPath: String? = null,

    @ColumnInfo(name = "tvShowOriginalName")
    val originalName: String? = null,

    @ColumnInfo(name = "tvShowPopularity")
    var popularity: Double? = null,

    @ColumnInfo(name = "tvShowVoteAverage")
    var voteAverage: Double? = null,


    @ColumnInfo(name = "tvShowVoteCount")
    var voteCount: Int? = null,

    @NonNull
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false

)