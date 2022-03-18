package com.raychal.moviesandtvshowsfinal.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.raychal.favorite.FavoriteActivity
import com.raychal.moviesandtvshowsfinal.R
import com.raychal.moviesandtvshowsfinal.databinding.ActivityMainBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpAdapter()
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