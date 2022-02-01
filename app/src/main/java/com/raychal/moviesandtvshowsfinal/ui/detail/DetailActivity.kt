package com.raychal.moviesandtvshowsfinal.ui.detail

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.raychal.moviesandtvshowsfinal.BuildConfig
import com.raychal.moviesandtvshowsfinal.R
import com.raychal.moviesandtvshowsfinal.data.source.local.entity.MovieEntity
import com.raychal.moviesandtvshowsfinal.data.source.local.entity.TvShowEntity
import com.raychal.moviesandtvshowsfinal.databinding.ActivityDetailBinding
import com.raychal.moviesandtvshowsfinal.utils.Constants
import com.raychal.moviesandtvshowsfinal.utils.Constants.TYPE_MOVIE
import com.raychal.moviesandtvshowsfinal.utils.Constants.TYPE_TVSHOW
import com.raychal.moviesandtvshowsfinal.utils.loadFromUrl
import com.raychal.moviesandtvshowsfinal.vm.ViewModelFactory
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupViewModel()

        val id = intent.getIntExtra(EXTRA_DATA, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        if (type.equals(TYPE_MOVIE, ignoreCase = true)) {
            setupToolbarTitle(resources.getString(R.string.toolbar_title_detail_movie))
            observeDetail(id, null)

        } else if (type.equals(TYPE_TVSHOW, ignoreCase = true)) {
            setupToolbarTitle(resources.getString(R.string.toolbar_title_detail_tvshow))
            observeDetail(null, id)
        }
    }

    private fun observeDetail(movieId: Int?, tvShowId: Int?) {
        if (movieId != null) {
            viewModel.getMovieDetail(movieId).observe(this, {
                displayData(it, null)
            })
        }
        else {
            if (tvShowId != null) {
                viewModel.getTvShowDetail(tvShowId).observe(this, {
                    displayData(null, it)
                })
            }
        }
    }

    private fun displayData(movie: MovieEntity?, tvShow: TvShowEntity?) {
        val urlImage = movie?.posterPath ?: tvShow?.posterPath
        val statusFavorite = movie?.isFavorite ?: tvShow?.isFavorite

        with(binding){
            title.text = movie?.title ?: tvShow?.originalName
            dateRelease.text = movie?.releaseDate ?: tvShow?.firstAirDate
            rating.text = (movie?.voteAverage ?: tvShow?.voteAverage.toString()).toString()
            synopsis.text = movie?.overview ?: tvShow?.overview
            voteCount.text = (movie?.voteCount ?: tvShow?.voteCount.toString()).toString()
            popularity.text = (movie?.popularity ?: tvShow?.popularity.toString()).toString()
            orisinalLanguage.text = movie?.originalLanguage ?: tvShow?.originalLanguage
        }

        statusFavorite?.let { status ->
            setFavoriteState(status)
        }

        binding.image.loadFromUrl(BuildConfig.BASE_URL_IMAGE_TMDB + Constants.ENDPOINT_POSTER_SIZE_W185 + urlImage)
        binding.fabFavorite.setOnClickListener {
            setFavorite(movie, tvShow)
        }

    }

    private fun setFavoriteState(status: Boolean){
        if (status) {
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite)
        } else {
            binding.fabFavorite.setImageResource(R.drawable.ic_unfavorite)
        }
    }

    private fun setFavorite(movie: MovieEntity?, tvShow: TvShowEntity?) {
        if (movie != null) {
            if (movie.isFavorite){
                FancyToast.makeText(
                    this, resources.getString(R.string.favorite_remove, "${movie.title}"), Toast.LENGTH_SHORT, FancyToast.ERROR, false
                ).show()
            }else {
                FancyToast.makeText(
                    this, resources.getString(R.string.favorite_add, "${movie.title}"), Toast.LENGTH_SHORT, FancyToast.SUCCESS, false
                ).show()
            }
            viewModel.setFavoriteMovie(movie)
        } else {
            if (tvShow != null) {
                if (tvShow.isFavorite){
                    FancyToast.makeText(
                        this, resources.getString(R.string.favorite_remove, "${tvShow.originalName}"), Toast.LENGTH_SHORT, FancyToast.ERROR, false
                    ).show()
                }else {
                    FancyToast.makeText(
                        this, resources.getString(R.string.favorite_add, "${tvShow.originalName}"), Toast.LENGTH_SHORT, FancyToast.SUCCESS, false
                    ).show()
                }
                viewModel.setFavoriteTvShow(tvShow)
            }
        }
    }

    private fun setupToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this@DetailActivity,
            factory
        )[DetailViewModel::class.java]
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }
}