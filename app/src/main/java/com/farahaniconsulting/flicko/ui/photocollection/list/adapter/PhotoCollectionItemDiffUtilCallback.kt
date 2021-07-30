package com.farahaniconsulting.flicko.ui.photocollection.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.farahaniconsulting.flicko.dto.PhotoItemDTO

class PhotoCollectionItemDiffUtilCallback : DiffUtil.ItemCallback<PhotoItemDTO>() {
    override fun areItemsTheSame(oldItem: PhotoItemDTO, newItem: PhotoItemDTO): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(oldItem: PhotoItemDTO, newItem: PhotoItemDTO): Boolean {
        return oldItem.description == newItem.description
    }
}