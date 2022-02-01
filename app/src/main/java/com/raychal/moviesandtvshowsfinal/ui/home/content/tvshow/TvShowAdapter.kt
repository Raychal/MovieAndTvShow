package com.raychal.moviesandtvshowsfinal.ui.home.content.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.raychal.moviesandtvshowsfinal.BuildConfig
import com.raychal.moviesandtvshowsfinal.data.source.local.entity.TvShowEntity
import com.raychal.moviesandtvshowsfinal.databinding.ItemRowDataBinding
import com.raychal.moviesandtvshowsfinal.utils.Constants
import com.raychal.moviesandtvshowsfinal.utils.loadFromUrl

class TvShowAdapter (private val callback: TvShowCallback) :
    PagedListAdapter<TvShowEntity, TvShowAdapter.ListViewHolder>(DIFF_CALLBACK) {
    override fun onBindViewHolder(holder: TvShowAdapter.ListViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemRowDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class ListViewHolder(private val binding: ItemRowDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TvShowEntity) {
            with(itemView) {
                data.posterPath?.let {
                    binding.imgData.loadFromUrl(BuildConfig.BASE_URL_IMAGE_TMDB + Constants.ENDPOINT_POSTER_SIZE_W185 + it)
                }
                binding.tvDataTitle.text = data.originalName
                binding.tvDataDate.text = data.firstAirDate
                binding.tvDataDesc.text = data.overview
                binding.cardItem.setOnClickListener {
                    callback.onItemClicked(data)
                }

            }

        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}