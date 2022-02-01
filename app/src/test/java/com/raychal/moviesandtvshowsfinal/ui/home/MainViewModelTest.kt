package com.raychal.moviesandtvshowsfinal.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.raychal.moviesandtvshowsfinal.data.source.CatalogRepository
import com.raychal.moviesandtvshowsfinal.data.source.local.entity.MovieEntity
import com.raychal.moviesandtvshowsfinal.data.source.local.entity.TvShowEntity
import com.raychal.moviesandtvshowsfinal.vo.Resource
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observerMovie: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var observerTvShow: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = MainViewModel(catalogRepository)
    }

    @Test
    fun getListNowPlayingMovies() {
        val dummyMovie = Resource.success(moviePagedList)
        Mockito.`when`(dummyMovie.data?.size).thenReturn(5)
        val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movie.value = dummyMovie

        Mockito.`when`(catalogRepository.getNowPlayingMovies()).thenReturn(movie)
        val movieEntity = viewModel.getListNowPlayingMovies().value?.data
        Mockito.verify(catalogRepository).getNowPlayingMovies()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getListNowPlayingMovies().observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(dummyMovie)
    }

    @Test
    fun getListOnTheAirTvShows() {
        val dummyTvShow = Resource.success(tvShowPagedList)
        Mockito.`when`(dummyTvShow.data?.size).thenReturn(5)
        val tvShow = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShow.value = dummyTvShow

        Mockito.`when`(catalogRepository.getTvShowOnTheAir()).thenReturn(tvShow)
        val tvShowEntity = viewModel.getListOnTheAirTvShows().value?.data
        Mockito.verify(catalogRepository).getTvShowOnTheAir()
        assertNotNull(tvShowEntity)
        assertEquals(5, tvShowEntity?.size)

        viewModel.getListOnTheAirTvShows().observeForever(observerTvShow)
        Mockito.verify(observerTvShow).onChanged(dummyTvShow)
    }
}