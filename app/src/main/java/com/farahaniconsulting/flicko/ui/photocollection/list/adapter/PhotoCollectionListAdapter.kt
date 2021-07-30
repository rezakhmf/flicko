package com.farahaniconsulting.flicko.ui.photocollection.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.farahaniconsulting.flicko.dto.PhotoItemDTO
import com.farahaniconsulting.flicko.ui.photocollection.list.viewholder.PhotoCollectionItemViewHolder

class PhotoCollectionListAdapter(private val itemClickHandler: ((PhotoItemDTO) -> Unit)) :
   ListAdapter<PhotoItemDTO, PhotoCollectionItemViewHolder>(PhotoCollectionItemDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoCollectionItemViewHolder {
        return PhotoCollectionItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PhotoCollectionItemViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it, itemClickHandler)
        }
    }
}