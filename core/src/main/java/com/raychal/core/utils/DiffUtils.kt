package com.raychal.core.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.raychal.core.domain.model.Game

class DiffUtils(private val oldList: List<Game>, private val newList: List<Game>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (backgroundImage,
            _,
            name,
            playtime,
            rating,
            released,
            backgroundImageAdditional,
            description,
            updated,
            favorite) = oldList[oldItemPosition]
        val (backgroundImage1,
            _,
            name1,
            playtime1,
            rating1,
            released1,
            backgroundImageAdditional1,
            description1,
            updated1,
            favorite1) = newList[newItemPosition]

        return backgroundImage == backgroundImage1
                && name == name1
                && playtime == playtime1
                && rating == rating1
                && released == released1
                && backgroundImageAdditional == backgroundImageAdditional1
                && description == description1
                && updated == updated1
                && favorite == favorite1
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}