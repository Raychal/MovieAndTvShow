package com.raychal.moviesandtvshowsfinal.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.raychal.moviesandtvshowsfinal.data.source.local.LocalDataSource
import com.raychal.moviesandtvshowsfinal.data.source.local.entity.MovieEntity
import com.raychal.moviesandtvshowsfinal.data.source.local.entity.TvShowEntity
import com.raychal.moviesandtvshowsfinal.data.source.remote.RemoteDataSource
import com.raychal.moviesandtvshowsfinal.data.source.remote.response.MovieResponse
import com.raychal.moviesandtvshowsfinal.data.source.remote.response.TvShowResponse
import com.raychal.moviesandtvshowsfinal.data.source.remote.vo.ApiResponse
import com.raychal.moviesandtvshowsfinal.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FakeCatalogRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : CatalogDataSource {

    override fun getNowPlayingMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>() {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getListMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()


            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getNowPlayingMovies()

            public override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (item in data) {
                    val movie = MovieEntity(
                        null,
                        item.id,
                        item.overview,
                        item.originalLanguage,
                        item.title,
                        item.posterPath,
                        item.releaseDate,
                        item.popularity,
                        item.voteAverage,
                        item.voteCount,
                        false
                    )
                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
            }

        }.asLiveData()
    }

    override fun getListFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getListFavoriteMovies(), config).build()
    }

    override fun getMovieDetail(movieId: Int): LiveData<MovieEntity> =
        localDataSource.getDetailMovie(movieId)


    override fun getTvShowOnTheAir(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>() {
            public override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getListTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getTvShowOnTheAir()


            public override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (item in data) {
                    val tvShow = TvShowEntity(
                        null,
                        item.id,
                        item.firstAirDate,
                        item.overview,
                        item.originalLanguage,
                        item.posterPath,
                        item.originalName,
                        item.popularity,
                        item.voteAverage,
                        item.voteCount,
                        false
                    )
                    tvShowList.add(tvShow)
                }

                localDataSource.insertTvShows(tvShowList)
            }

        }.asLiveData()
    }

    override fun getListFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getListFavoriteTvShows(), config).build()
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity> =
        localDataSource.getDetailTvShow(tvShowId)

    override fun setFavoriteMovie(movie: MovieEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setFavoriteMovie(movie)
        }
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setFavoriteTvShow(tvShow)
        }
    }
}