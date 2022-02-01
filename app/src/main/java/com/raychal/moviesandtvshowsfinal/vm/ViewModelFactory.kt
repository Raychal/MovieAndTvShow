package com.raychal.moviesandtvshowsfinal.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raychal.moviesandtvshowsfinal.data.source.CatalogRepository
import com.raychal.moviesandtvshowsfinal.ui.detail.DetailViewModel
import com.raychal.moviesandtvshowsfinal.ui.home.MainViewModel
import com.raychal.moviesandtvshowsfinal.ui.home.content.favorite.FavoriteViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val mCatalogRepository: CatalogRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(mCatalogRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }

}