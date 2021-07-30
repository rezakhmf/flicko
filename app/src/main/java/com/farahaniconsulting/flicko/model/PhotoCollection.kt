package com.farahaniconsulting.flicko.model

import com.google.gson.annotations.SerializedName

data class PhotoCollection(
    val description: String?,
    val generator: String?,
    val items: List<Item>?,
    val link: String?,
    val modified: String?,
    val title: String?
)