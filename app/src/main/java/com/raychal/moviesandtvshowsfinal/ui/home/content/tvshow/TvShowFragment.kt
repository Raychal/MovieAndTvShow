package com.raychal.moviesandtvshowsfinal.ui.home.content.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.raychal.moviesandtvshowsfinal.data.source.local.entity.TvShowEntity
import com.raychal.moviesandtvshowsfinal.databinding.FragmentTvShowBinding
import com.raychal.moviesandtvshowsfinal.ui.detail.DetailActivity
import com.raychal.moviesandtvshowsfinal.ui.home.MainViewModel
import com.raychal.moviesandtvshowsfinal.utils.Constants.TYPE_TVSHOW
import com.raychal.moviesandtvshowsfinal.vm.ViewModelFactory
import com.raychal.moviesandtvshowsfinal.vo.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TvShowFragment : DaggerFragment(), TvShowCallback {
    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        activity?.let { setupViewModel(it) }
        observeListTvShow()

    }

    private fun setupViewModel(fragmentActivity: FragmentActivity) {
        fragmentActivity.let {
            viewModel = ViewModelProvider(
                it,
                factory
            )[MainViewModel::class.java]
        }
    }

    private fun observeListTvShow() {
        viewModel.getListOnTheAirTvShows().observe(viewLifecycleOwner, { listTvShow ->
            if (listTvShow != null) {
                when (listTvShow.status) {
                    Status.LOADING -> binding.pbTvshow.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.pbTvshow.progressBar.visibility = View.GONE
                        binding.rvTvshow.adapter?.let { adapter ->
                            when (adapter) {
                                is TvShowAdapter -> {
                                    adapter.submitList(listTvShow.data)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                    Status.ERROR -> {
                        binding.pbTvshow.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        binding.rvTvshow.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TvShowAdapter(this@TvShowFragment)
        }
    }

    override fun onItemClicked(data: TvShowEntity) {
        startActivity(
            Intent(
                context,
                DetailActivity::class.java
            )
                .putExtra(DetailActivity.EXTRA_DATA, data.tvShowId)
                .putExtra(DetailActivity.EXTRA_TYPE, TYPE_TVSHOW)
        )
    }
}