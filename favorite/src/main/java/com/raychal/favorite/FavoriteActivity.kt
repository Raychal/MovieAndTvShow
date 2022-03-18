package com.raychal.favorite

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.raychal.favorite.databinding.ActivityFavoriteBinding
import com.raychal.favorite.di.favoriteModule
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Favorite"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupViewPager()
    }

    private fun setupViewPager() {
        loadKoinModules(favoriteModule)
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