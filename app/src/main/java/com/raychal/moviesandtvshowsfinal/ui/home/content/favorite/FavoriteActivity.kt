package com.raychal.moviesandtvshowsfinal.ui.home.content.favorite

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.raychal.moviesandtvshowsfinal.databinding.ActivityFavoriteBinding
import com.raychal.moviesandtvshowsfinal.vm.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class FavoriteActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Favorite"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupViewPager()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this@FavoriteActivity,
            factory
        )[FavoriteViewModel::class.java]
    }

    private fun setupViewPager() {
        val myPagerAdapter = SectionPagerAdapterFavorite(supportFragmentManager, lifecycle)
        with(binding){
            pagerFav.adapter = myPagerAdapter
            TabLayoutMediator(binding.tabsFav, binding.pagerFav){tab, position ->
                when (position) {
                    0 -> {
                        tab.text = "Movies"
                    }
                    1 -> {
                        tab.text = "Tv Shows"
                    }
                }
            }.attach()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }
}