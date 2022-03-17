package com.raychal.core.data.source.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.raychal.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogDao {

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getMovies(query: SupportSQLiteQuery): Flow<List<MovieEntity>>

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getTvShows(query: SupportSQLiteQuery): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieEntities WHERE isTvShow = 0 AND movieTitle LIKE '%' || :search || '%'")
    fun getSearchMovies(search: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieEntities WHERE isTvShow = 1 AND movieTitle LIKE '%' || :search || '%'")
    fun getSearchTvShows(search: String): Flow<List<MovieEntity>>

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getFavoriteMovies(query: SupportSQLiteQuery): Flow<List<MovieEntity>>

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getFavoriteTvShows(query: SupportSQLiteQuery): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)

}