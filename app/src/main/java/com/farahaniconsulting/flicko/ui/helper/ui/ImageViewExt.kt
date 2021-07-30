package com.farahaniconsulting.flicko.ui.helper.ui

import android.widget.ImageView
import com.farahanconsulting.flicko.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun ImageView.load(imageUrl: String) {
    if (imageUrl.isNullOrEmpty() || imageUrl.isBlank()) {
        this.setImageDrawable(this.context.getDrawable(R.drawable.ic_empty_image)!!)
    } else {
        Picasso.get()
            .load(imageUrl)
            .error(this.context.getDrawable(R.drawable.ic_cross_image)!!)
            .into(this)
    }
}

fun ImageView.load(imageUrl: String?, callback: ImageLoadingCallback) {
    Picasso.get()
        .load(imageUrl)
        .into(this, object : Callback {
            override fun onSuccess() = callback.onLoadingSuccess()

            override fun onError(e: Exception?) = callback.onError(e)
        })
}

interface ImageLoadingCallback {
    fun onLoadingSuccess()
    fun onError(e: Exception?)
}