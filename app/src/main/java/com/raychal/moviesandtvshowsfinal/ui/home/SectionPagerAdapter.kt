package com.raychal.moviesandtvshowsfinal.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.raychal.moviesandtvshowsfinal.ui.movies.MovieFragment
import com.raychal.moviesandtvshowsfinal.ui.tvshows.TvShowFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class SectionPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment = Fragment()
        when (position) {
            0 -> fragment = MovieFragment()
            1 -> fragment = TvShowFragment()
        }
        return fragment
    }

}