package com.raychal.moviesandtvshowsfinal.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.raychal.moviesandtvshowsfinal.LiveDataTestUtil
import com.raychal.moviesandtvshowsfinal.PagedListUtil
import com.raychal.moviesandtvshowsfinal.data.source.local.LocalDataSource
import com.raychal.moviesandtvshowsfinal.data.source.local.entity.MovieEntity
import com.raychal.moviesandtvshowsfinal.data.source.local.entity.TvShowEntity
import com.raychal.moviesandtvshowsfinal.data.source.remote.RemoteDataSource
import com.raychal.moviesandtvshowsfinal.utils.DataDummy
import com.raychal.moviesandtvshowsfinal.vo.Resource
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class CatalogRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val catalogRepository = FakeCatalogRepository(remote, local)

    private val listMovie = DataDummy.generateDataMovieDummy()
    private val listTvShow = DataDummy.generateDataTvShowDummy()
    private val movie = DataDummy.generateDataMovieDummy()[0]
    private val tvShow = DataDummy.generateDataTvShowDummy()[0]


    @Test
    fun getNowPlayingMovies() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getListMovies()).thenReturn(dataSourceFactory)
        catalogRepository.getNowPlayingMovies()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataMovieDummy()))
        Mockito.verify(local).getListMovies()
        org.junit.Assert.assertNotNull(movieEntity.data)
        assertEquals(listMovie.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getTvShowOnTheAir() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(local.getListTvShows()).thenReturn(dataSourceFactory)
        catalogRepository.getTvShowOnTheAir()

        val tvShowEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataTvShowDummy()))
        Mockito.verify(local).getListTvShows()
        org.junit.Assert.assertNotNull(tvShowEntity.data)
        assertEquals(listTvShow.size.toLong(), tvShowEntity.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = movie
        Mockito.`when`(local.getDetailMovie(movie.movieId)).thenReturn(dummyMovie)

        val data = LiveDataTestUtil.getValue(catalogRepository.getMovieDetail(movie.movieId))
        Mockito.verify(local).getDetailMovie(movie.movieId)
        org.junit.Assert.assertNotNull(data)
        assertEquals(movie.movieId, data.movieId)
    }

    @Test
    fun getTvShowDetail() {
        val dummyTvShow = MutableLiveData<TvShowEntity>()
        dummyTvShow.value = tvShow
        Mockito.`when`(local.getDetailTvShow(tvShow.tvShowId)).thenReturn(dummyTvShow)

        val data = LiveDataTestUtil.getValue(catalogRepository.getTvShowDetail(tvShow.tvShowId))
        Mockito.verify(local).getDetailTvShow(tvShow.tvShowId)
        org.junit.Assert.assertNotNull(data)
        assertEquals(tvShow.tvShowId, data.tvShowId)
    }

    @Test
    fun getListFavoriteMovies() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getListFavoriteMovies()).thenReturn(dataSourceFactory)
        catalogRepository.getListFavoriteMovies()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataMovieDummy()))
        Mockito.verify(local).getListFavoriteMovies()
        org.junit.Assert.assertNotNull(movieEntity.data)
        assertEquals(listMovie.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getListFavoriteTvShows() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(local.getListFavoriteTvShows()).thenReturn(dataSourceFactory)
        catalogRepository.getListFavoriteTvShows()

        val tvShowEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataTvShowDummy()))
        Mockito.verify(local).getListFavoriteTvShows()
        org.junit.Assert.assertNotNull(tvShowEntity.data)
        assertEquals(listTvShow.size.toLong(), tvShowEntity.data?.size?.toLong())
    }
}