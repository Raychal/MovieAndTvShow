package com.raychal.moviesandtvshowsfinal.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.raychal.moviesandtvshowsfinal.R
import com.raychal.moviesandtvshowsfinal.databinding.ActivityMainBinding
import com.raychal.moviesandtvshowsfinal.ui.home.content.favorite.FavoriteActivity
import com.raychal.moviesandtvshowsfinal.vm.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setUpAdapter()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this@MainActivity,
            factory
        )[MainViewModel::class.java]
    }

    private fun setUpAdapter() {
        val myPagerAdapter = SectionPagerAdapter(supportFragmentManager, lifecycle)
        with(binding){
            pager.adapter = myPagerAdapter
            TabLayoutMediator(binding.tabsMain, binding.pager){tab, position ->
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.favorite_destination -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
            }
        }
    }
}