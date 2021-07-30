package com.farahaniconsulting.flicko.ui.photocollection.list.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.farahanconsulting.flicko.R
import com.farahanconsulting.flicko.databinding.PhotoCollectionItemBinding
import com.farahaniconsulting.flicko.dto.PhotoItemDTO

class PhotoCollectionItemViewHolder(private val viewBinding: PhotoCollectionItemBinding) :
   RecyclerView.ViewHolder(viewBinding.root)  {

       fun bind(photoItemItem: PhotoItemDTO, callback: ((PhotoItemDTO)) -> Unit) {
           viewBinding.photoItem = photoItemItem
           viewBinding.executePendingBindings()

           viewBinding.root.setOnClickListener {
               viewBinding.photoItem?.let { callback.invoke(it) }
           }
       }

    companion object {
        fun create(parent: ViewGroup) : PhotoCollectionItemViewHolder {
            return PhotoCollectionItemViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.photo_collection_item, parent, false
                )
            )
        }
    }
}