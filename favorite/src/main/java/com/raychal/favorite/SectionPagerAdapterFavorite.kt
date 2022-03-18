package com.raychal.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.raychal.favorite.movies.FavoriteMoviesFragment
import com.raychal.favorite.tvshows.FavoriteTvShowsFragment

class SectionPagerAdapterFavorite(fragmentManager: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment = Fragment()
        when (position) {
            0 -> fragment = FavoriteMoviesFragment()
            1 -> fragment = FavoriteTvShowsFragment()
        }
        return fragment
    }
}