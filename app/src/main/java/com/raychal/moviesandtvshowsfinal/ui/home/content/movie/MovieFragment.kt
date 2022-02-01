package com.raychal.moviesandtvshowsfinal.ui.home.content.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.raychal.moviesandtvshowsfinal.data.source.local.entity.MovieEntity
import com.raychal.moviesandtvshowsfinal.databinding.FragmentMovieBinding
import com.raychal.moviesandtvshowsfinal.ui.detail.DetailActivity
import com.raychal.moviesandtvshowsfinal.ui.home.MainViewModel
import com.raychal.moviesandtvshowsfinal.utils.Constants.TYPE_MOVIE
import com.raychal.moviesandtvshowsfinal.vm.ViewModelFactory
import com.raychal.moviesandtvshowsfinal.vo.Status
import dagger.android.support.DaggerFragment

import javax.inject.Inject

class MovieFragment : DaggerFragment(), MovieCallback {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        activity?.let { setupViewModel(it) }
        observeListMovies()

    }

    private fun setupViewModel(fragmentActivity: FragmentActivity) {
        fragmentActivity.let {
            viewModel = ViewModelProvider(
                it,
                factory
            )[MainViewModel::class.java]
        }
    }

    private fun observeListMovies() {
        viewModel.getListNowPlayingMovies().observe(viewLifecycleOwner, { listMovie ->
            if (listMovie != null) {
                when (listMovie.status) {
                    Status.LOADING -> binding.pbMovie.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.pbMovie.progressBar.visibility = View.GONE
                        binding.rvMovie.adapter?.let { adapter ->
                            when (adapter) {
                                is MovieAdapter -> {
                                    adapter.submitList(listMovie.data)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                    Status.ERROR -> {
                        binding.pbMovie.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MovieAdapter(this@MovieFragment)
        }
    }

    override fun onItemClicked(data: MovieEntity) {
        startActivity(
            Intent(
                context,
                DetailActivity::class.java
            )
                .putExtra(DetailActivity.EXTRA_DATA, data.movieId)
                .putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE)
        )
    }

}