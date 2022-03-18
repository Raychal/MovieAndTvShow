package com.raychal.moviesandtvshowsfinal.ui.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.raychal.core.data.Resource
import com.raychal.core.domain.model.Movie
import com.raychal.core.ui.MoviesAdapter
import com.raychal.core.utils.SortUtils
import com.raychal.moviesandtvshowsfinal.R
import com.raychal.moviesandtvshowsfinal.databinding.FragmentTvShowBinding
import com.raychal.moviesandtvshowsfinal.ui.detail.DetailActivity
import com.raychal.moviesandtvshowsfinal.ui.home.MainActivity
import com.raychal.moviesandtvshowsfinal.ui.home.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {
    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        val toolbar: Toolbar = activity?.findViewById<View>(R.id.toolbar) as Toolbar
        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        searchView = (activity as MainActivity).findViewById(R.id.search_view)
        return binding.root
    }

    private val viewModel: TvShowsViewModel by viewModel()
    private lateinit var tvShowsAdapter: MoviesAdapter
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var searchView: MaterialSearchView
    private var sort = SortUtils.RANDOM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowsAdapter = MoviesAdapter()
        setList(sort)
        observeSearchQuery()
        setSearchList()

        with(binding.rvTvshow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowsAdapter
        }

        tvShowsAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MOVIE, selectedData)
            startActivity(intent)
        }

        binding.random.setOnClickListener {
            binding.menu.close(true)
            sort = SortUtils.RANDOM
            setList(sort)
        }
        binding.newest.setOnClickListener {
            binding.menu.close(true)
            sort = SortUtils.NEWEST
            setList(sort)
        }
        binding.popularity.setOnClickListener {
            binding.menu.close(true)
            sort = SortUtils.POPULARITY
            setList(sort)
        }
        binding.vote.setOnClickListener {
            binding.menu.close(true)
            sort = SortUtils.VOTE
            setList(sort)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val item = menu.findItem(R.id.action_search)
        searchView.setMenuItem(item)
    }

    private fun setList(sort: String) {
        viewModel.getTvShows(sort).observe(viewLifecycleOwner, tvShowsObserver)
    }

    private val tvShowsObserver = Observer<Resource<List<Movie>>> { tvShow ->
        if (tvShow != null) {
            when (tvShow) {
                is Resource.Loading -> {
                    binding.pbTvshow.progressBar.visibility = View.VISIBLE
                    binding.notFound.visibility = View.GONE
                    binding.notFoundText.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.pbTvshow.progressBar.visibility = View.GONE
                    binding.notFound.visibility = View.GONE
                    binding.notFoundText.visibility = View.GONE
                    tvShowsAdapter.setData(tvShow.data)
                }
                is Resource.Error -> {
                    binding.pbTvshow.progressBar.visibility = View.GONE
                    binding.notFound.visibility = View.VISIBLE
                    binding.notFoundText.visibility = View.VISIBLE
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun observeSearchQuery() {
        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    mainViewModel.setSearchQuery(it)
                }
                return true
            }

        })
    }

    private fun setSearchList() {
        mainViewModel.tvShowResult.observe(viewLifecycleOwner, { tvShows ->
            if (tvShows.isNullOrEmpty()) {
                binding.pbTvshow.progressBar.visibility = View.GONE
                binding.notFound.visibility = View.VISIBLE
                binding.notFoundText.visibility = View.VISIBLE
            } else {
                binding.pbTvshow.progressBar.visibility = View.GONE
                binding.notFound.visibility = View.GONE
                binding.notFoundText.visibility = View.GONE
            }
            tvShowsAdapter.setData(tvShows)
        })
        searchView.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener{
            override fun onSearchViewShown() {
                binding.pbTvshow.progressBar.visibility = View.GONE
                binding.notFound.visibility = View.GONE
                binding.notFoundText.visibility = View.GONE
            }

            override fun onSearchViewClosed() {
                binding.pbTvshow.progressBar.visibility = View.GONE
                binding.notFound.visibility = View.GONE
                binding.notFoundText.visibility = View.GONE
                setList(sort)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}