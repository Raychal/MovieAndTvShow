package com.raychal.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {

    const val POPULARITY = "Popularity"
    const val VOTE = "Vote"
    const val NEWEST = "Newest"
    const val RANDOM = "Random"

    fun getSortedQueryGames(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM gameEntities")
        when (filter) {
            POPULARITY -> {
                simpleQuery.append("ORDER BY playtime DESC")
            }
            NEWEST -> {
                simpleQuery.append("ORDER BY released DESC")
            }
            VOTE -> {
                simpleQuery.append("ORDER BY rating DESC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryFavoriteGames(filter: String): SimpleSQLiteQuery {
        val simpleQuery =
            StringBuilder().append("SELECT * FROM gameEntities where favorite = 1")
        when (filter) {
            POPULARITY -> {
                simpleQuery.append("ORDER BY playtime DESC")
            }
            NEWEST -> {
                simpleQuery.append("ORDER BY released DESC")
            }
            VOTE -> {
                simpleQuery.append("ORDER BY rating DESC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}