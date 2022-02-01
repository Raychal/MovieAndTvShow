package com.raychal.moviesandtvshowsfinal.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.raychal.moviesandtvshowsfinal.data.source.CatalogRepository
import com.raychal.moviesandtvshowsfinal.data.source.local.entity.MovieEntity
import com.raychal.moviesandtvshowsfinal.data.source.local.entity.TvShowEntity
import com.raychal.moviesandtvshowsfinal.utils.DataDummy
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private val dummyMovie = DataDummy.generateDataMovieDummy()[0]
    private val movieId = dummyMovie.movieId
    private val dummyTvShow = DataDummy.generateDataTvShowDummy()[0]
    private val tvShowId = dummyTvShow.tvShowId

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observerMovie: Observer<MovieEntity>

    @Mock
    private lateinit var observerTvShow: Observer<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(catalogRepository)
    }

    @Test
    fun getMovieDetail() {
        val movieDummy = MutableLiveData<MovieEntity>()
        movieDummy.value = dummyMovie

        Mockito.`when`(catalogRepository.getMovieDetail(movieId)).thenReturn(movieDummy)

        val movieData = viewModel.getMovieDetail(movieId).value

        Assert.assertNotNull(movieData)
        assertEquals(dummyMovie.id, movieData?.id)
        assertEquals(dummyMovie.movieId, movieData?.movieId)
        assertEquals(dummyMovie.overview, movieData?.overview)
        assertEquals(dummyMovie.originalLanguage, movieData?.originalLanguage)
        assertEquals(dummyMovie.title, movieData?.title)
        assertEquals(dummyMovie.posterPath, movieData?.posterPath)
        assertEquals(dummyMovie.releaseDate, movieData?.releaseDate)
        assertEquals(dummyMovie.popularity, movieData?.popularity)
        assertEquals(dummyMovie.voteAverage, movieData?.voteAverage)
        assertEquals(dummyMovie.voteCount, movieData?.voteCount)

        viewModel.getMovieDetail(movieId).observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)

    }

    @Test
    fun getTvShowDetail() {
        val tvShowDummy = MutableLiveData<TvShowEntity>()
        tvShowDummy.value = dummyTvShow

        Mockito.`when`(catalogRepository.getTvShowDetail(tvShowId)).thenReturn(tvShowDummy)

        val tvShowData = viewModel.getTvShowDetail(tvShowId).value

        Assert.assertNotNull(tvShowData)
        assertEquals(dummyTvShow.id, tvShowData?.id)
        assertEquals(dummyTvShow.tvShowId, tvShowData?.tvShowId)
        assertEquals(dummyTvShow.firstAirDate, tvShowData?.firstAirDate)
        assertEquals(dummyTvShow.overview, tvShowData?.overview)
        assertEquals(dummyTvShow.originalLanguage, tvShowData?.originalLanguage)
        assertEquals(dummyTvShow.posterPath, tvShowData?.posterPath)
        assertEquals(dummyTvShow.originalName, tvShowData?.originalName)
        assertEquals(dummyTvShow.popularity, tvShowData?.popularity)
        assertEquals(dummyTvShow.voteAverage, tvShowData?.voteAverage)
        assertEquals(dummyTvShow.voteCount, tvShowData?.voteCount)

        viewModel.getTvShowDetail(tvShowId).observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummyTvShow)
    }
}