package com.raychal.core.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.raychal.core.domain.model.Movie

class DiffUtils(private val oldList: List<Movie>, private val newList: List<Movie>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val (_,
            overview,
            originalLanguage,
            title,
            posterPath,
            releaseDate,
            popularity,
            voteAverage,
            voteCount,
            favorite,
            isTvShows) = oldList[oldPosition]
        val (_,
            overview1,
            originalLanguage1,
            title1,
            posterPath1,
            releaseDate1,
            popularity1,
            voteAverage1,
            voteCount1,
            favorite1,
            isTvShows1) = newList[newPosition]

        return overview == overview1
                && originalLanguage == originalLanguage1
                && title == title1
                && posterPath == posterPath1
                && releaseDate == releaseDate1
                && popularity == popularity1
                && voteAverage == voteAverage1
                && voteCount == voteCount1
                && favorite == favorite1
                && isTvShows == isTvShows1
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}