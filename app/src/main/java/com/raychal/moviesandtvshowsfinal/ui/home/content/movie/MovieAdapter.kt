package com.raychal.moviesandtvshowsfinal.ui.home.content.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.raychal.moviesandtvshowsfinal.BuildConfig
import com.raychal.moviesandtvshowsfinal.data.source.local.entity.MovieEntity
import com.raychal.moviesandtvshowsfinal.databinding.ItemRowDataBinding
import com.raychal.moviesandtvshowsfinal.utils.Constants
import com.raychal.moviesandtvshowsfinal.utils.loadFromUrl

class MovieAdapter(private val callback: MovieCallback) :
    PagedListAdapter<MovieEntity, MovieAdapter.ListViewHolder>(DIFF_CALLBACK) {
    override fun onBindViewHolder(holder: MovieAdapter.ListViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ListViewHolder {
        return ListViewHolder(
            ItemRowDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class ListViewHolder(private val binding: ItemRowDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieEntity) {
            with(itemView) {
                data.posterPath?.let {
                    binding.imgData.loadFromUrl(BuildConfig.BASE_URL_IMAGE_TMDB + Constants.ENDPOINT_POSTER_SIZE_W185 + it)
                }
                binding.tvDataTitle.text = data.title
                binding.tvDataDate.text = data.releaseDate
                binding.tvDataDesc.text = data.overview
                binding.cardItem.setOnClickListener {
                    callback.onItemClicked(data)
                }

            }

        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}