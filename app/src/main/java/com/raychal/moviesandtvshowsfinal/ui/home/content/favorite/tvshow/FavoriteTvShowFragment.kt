package com.raychal.moviesandtvshowsfinal.ui.home.content.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.raychal.moviesandtvshowsfinal.R
import com.raychal.moviesandtvshowsfinal.data.source.local.entity.TvShowEntity
import com.raychal.moviesandtvshowsfinal.databinding.FragmentFavoriteTvShowBinding
import com.raychal.moviesandtvshowsfinal.ui.detail.DetailActivity
import com.raychal.moviesandtvshowsfinal.ui.home.content.favorite.FavoriteViewModel
import com.raychal.moviesandtvshowsfinal.ui.home.content.tvshow.TvShowAdapter
import com.raychal.moviesandtvshowsfinal.ui.home.content.tvshow.TvShowCallback
import com.raychal.moviesandtvshowsfinal.utils.Constants
import com.raychal.moviesandtvshowsfinal.vm.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavoriteTvShowFragment : DaggerFragment(), TvShowCallback {

    private var _binding: FragmentFavoriteTvShowBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { setupViewModel(it) }
        setupRecyclerView()
        observeFavoriteTvShow()
    }

    private fun setupViewModel(fragmentActivity: FragmentActivity) {
        fragmentActivity.let {
            viewModel = ViewModelProvider(
                it,
                factory
            )[FavoriteViewModel::class.java]
        }
    }

    private fun observeFavoriteTvShow() {
        viewModel.getListFavoriteTvShow().observe(viewLifecycleOwner, {
            if (it != null){
                binding.rvFavoriteTvshow.adapter?.let {adapter ->
                    when (adapter) {
                        is TvShowAdapter -> {
                            if (it.isNullOrEmpty()){
                                binding.rvFavoriteTvshow.visibility = View.GONE
                                enableEmptyStateEmptyFavoriteTvShow()
                            } else {
                                binding.rvFavoriteTvshow.visibility = View.VISIBLE
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
        binding.rvFavoriteTvshow.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TvShowAdapter(this@FavoriteTvShowFragment)
        }
    }

    private fun enableEmptyStateEmptyFavoriteTvShow() {
        binding.favoriteTvshowEmptyState.titleEmptyState.text = resources.getString(R.string.empty_state_title_no_favorite_item)
        binding.favoriteTvshowEmptyState.descEmptyState.text =
            resources.getString(R.string.empty_state_desc_no_favorite_item_tvshow)
        binding.favoriteTvshowEmptyState.empty.visibility = View.VISIBLE
    }

    override fun onItemClicked(data: TvShowEntity) {
        startActivity(
            Intent(
                context,
                DetailActivity::class.java
            )
                .putExtra(DetailActivity.EXTRA_DATA, data.tvShowId)
                .putExtra(DetailActivity.EXTRA_TYPE, Constants.TYPE_TVSHOW)
        )
    }
}