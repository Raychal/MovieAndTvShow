package com.raychal.moviesandtvshowsfinal.ui.home.content.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.raychal.moviesandtvshowsfinal.R
import com.raychal.moviesandtvshowsfinal.data.source.local.entity.MovieEntity
import com.raychal.moviesandtvshowsfinal.databinding.FragmentFavoriteMovieBinding
import com.raychal.moviesandtvshowsfinal.ui.detail.DetailActivity
import com.raychal.moviesandtvshowsfinal.ui.home.content.favorite.FavoriteViewModel
import com.raychal.moviesandtvshowsfinal.ui.home.content.movie.MovieAdapter
import com.raychal.moviesandtvshowsfinal.ui.home.content.movie.MovieCallback
import com.raychal.moviesandtvshowsfinal.utils.Constants
import com.raychal.moviesandtvshowsfinal.vm.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavoriteMovieFragment : DaggerFragment(), MovieCallback {

    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        activity?.let { setupViewModel(it) }
        observeFavoriteMovies()
    }

    private fun setupViewModel(fragmentActivity: FragmentActivity) {
        fragmentActivity.let {
            viewModel = ViewModelProvider(
                it,
                factory
            )[FavoriteViewModel::class.java]
        }
    }

    private fun observeFavoriteMovies() {
        viewModel.getListFavoriteMovie().observe(viewLifecycleOwner, {
            if (it != null){
                binding.rvFavoriteMovie.adapter?.let {adapter ->
                    when (adapter) {
                        is MovieAdapter -> {
                            if (it.isNullOrEmpty()){
                                binding.rvFavoriteMovie.visibility = View.GONE
                                enableEmptyStateEmptyFavoriteMovie()
                            } else {
                                binding.rvFavoriteMovie.visibility = View.VISIBLE
                                adapter.submitList(it)
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        binding.rvFavoriteMovie.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MovieAdapter(this@FavoriteMovieFragment)
        }
    }

    private fun enableEmptyStateEmptyFavoriteMovie() {
        binding.favoriteMovieEmptyState.titleEmptyState.text = resources.getString(R.string.empty_state_title_no_favorite_item)
        binding.favoriteMovieEmptyState.descEmptyState.text =
            resources.getString(R.string.empty_state_desc_no_favorite_item_tvshow)
        binding.favoriteMovieEmptyState.empty.visibility = View.VISIBLE
    }

    override fun onItemClicked(data: MovieEntity) {
        startActivity(
            Intent(
                context,
                DetailActivity::class.java
            )
                .putExtra(DetailActivity.EXTRA_DATA, data.movieId)
                .putExtra(DetailActivity.EXTRA_TYPE, Constants.TYPE_MOVIE)
        )
    }
}