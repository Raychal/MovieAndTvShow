package com.raychal.moviesandtvshowsfinal.ui.home.content.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.raychal.moviesandtvshowsfinal.ui.home.content.favorite.movie.FavoriteMovieFragment
import com.raychal.moviesandtvshowsfinal.ui.home.content.favorite.tvshow.FavoriteTvShowFragment

class SectionPagerAdapterFavorite(fragmentManager: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment = Fragment()
        when (position) {
            0 -> fragment = FavoriteMovieFragment()
            1 -> fragment = FavoriteTvShowFragment()
        }
        return fragment
    }
}