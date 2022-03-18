package com.raychal.moviesandtvshowsfinal.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import com.bumptech.glide.Glide
import com.raychal.core.domain.model.Movie
import com.raychal.moviesandtvshowsfinal.R
import com.raychal.moviesandtvshowsfinal.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        if (detailMovie != null) {
            populateDetail(detailMovie)
        }

        binding.backButton.setOnClickListener { onBackPressed() }
        binding.share.setOnClickListener { share() }
    }

    @SuppressLint("StringFormatInvalid")
    private fun populateDetail(movie: Movie) {
        with(binding) {
            titleDetail.text = movie.title
            date.text = movie.releaseDate
            overview.text = movie.overview
            popularity.text = getString(
                R.string.popularity_detail,
                movie.popularity.toString(),
                movie.voteCount.toString(),
                movie.voteAverage.toString()
            )
            userScore.text = movie.voteAverage.toString()
            Glide.with(this@DetailActivity)
                .load(getString(R.string.baseUrlImage, movie.backdropPath))
                .into(posterTopBar)
            posterTopBar.tag = movie.posterPath

            Glide.with(this@DetailActivity)
                .load(getString(R.string.baseUrlImage, movie.posterPath))
                .into(subPoster)
            subPoster.tag = movie.posterPath

            var favoriteState = movie.favorite
            setFavoriteState(favoriteState)
            binding.favoriteButton.setOnClickListener {
                favoriteState = !favoriteState
                viewModel.setFavoriteMovie(movie, favoriteState)
                setFavoriteState(favoriteState)
            }
        }
    }

    private fun setFavoriteState(status: Boolean){
        if (status) {
            binding.favoriteButton.setImageResource(R.drawable.ic_favorite)
        } else {
            binding.favoriteButton.setImageResource(R.drawable.ic_unfavorite)
        }
    }

    private fun share() {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder.from(this).apply {
            setType(mimeType)
            setChooserTitle(getString(R.string.shareTitle))
            setText(getString(R.string.shareBody))
            startChooser()
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extraMovie"
    }
}