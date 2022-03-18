package com.raychal.moviesandtvshowsfinal.ui.movies

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
import com.raychal.moviesandtvshowsfinal.databinding.FragmentMovieBinding
import com.raychal.moviesandtvshowsfinal.ui.detail.DetailActivity
import com.raychal.moviesandtvshowsfinal.ui.home.MainActivity
import com.raychal.moviesandtvshowsfinal.ui.home.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        val toolbar: Toolbar = activity?.findViewById<View>(R.id.toolbar) as Toolbar
        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        searchView = (activity as MainActivity).findViewById(R.id.search_view)
        return binding.root
    }

    private val viewModel: MoviesViewModel by viewModel()
    private lateinit var moviesAdapter: MoviesAdapter
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var searchView: MaterialSearchView
    private var sort = SortUtils.RANDOM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesAdapter = MoviesAdapter()
        setList(sort)
        observeSearchQuery()
        setSearchList()

        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = moviesAdapter
        }

        moviesAdapter.onItemClick = { selectedData ->
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
        viewModel.getMovies(sort).observe(viewLifecycleOwner, moviesObserver)
    }

    private val moviesObserver = Observer<Resource<List<Movie>>> { movies ->
        if (movies != null) {
            when (movies) {
                is Resource.Loading -> {
                    binding.pbMovie.progressBar.visibility = View.VISIBLE
                    binding.notFound.visibility = View.GONE
                    binding.notFoundText.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.pbMovie.progressBar.visibility = View.GONE
                    binding.notFound.visibility = View.GONE
                    binding.notFoundText.visibility = View.GONE
                    moviesAdapter.setData(movies.data)
                }
                is Resource.Error -> {
                    binding.pbMovie.progressBar.visibility = View.GONE
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
        mainViewModel.movieResult.observe(viewLifecycleOwner, { movies ->
            if (movies.isNullOrEmpty()){
                binding.pbMovie.progressBar.visibility = View.GONE
                binding.notFound.visibility = View.VISIBLE
                binding.notFoundText.visibility = View.VISIBLE
            } else {
                binding.pbMovie.progressBar.visibility = View.GONE
                binding.notFound.visibility = View.GONE
                binding.notFoundText.visibility = View.GONE
            }
            moviesAdapter.setData(movies)
        })
        searchView.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener{
            override fun onSearchViewShown() {
                binding.pbMovie.progressBar.visibility = View.GONE
                binding.notFound.visibility = View.GONE
                binding.notFoundText.visibility = View.GONE
            }

            override fun onSearchViewClosed() {
                binding.pbMovie.progressBar.visibility = View.GONE
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

//    private fun observeListMovies() {
//        viewModel.getListNowPlayingMovies().observe(viewLifecycleOwner, { listMovie ->
//            if (listMovie != null) {
//                when (listMovie.status) {
//                    Status.LOADING -> binding.pbMovie.progressBar.visibility = View.VISIBLE
//                    Status.SUCCESS -> {
//                        binding.pbMovie.progressBar.visibility = View.GONE
//                        binding.rvMovie.adapter?.let { adapter ->
//                            when (adapter) {
//                                is MovieAdapter -> {
//                                    adapter.submitList(listMovie.data)
//                                    adapter.notifyDataSetChanged()
//                                }
//                            }
//                        }
//                    }
//                    Status.ERROR -> {
//                        binding.pbMovie.progressBar.visibility = View.GONE
//                        Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        })
//    }
}