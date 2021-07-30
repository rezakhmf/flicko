package com.farahaniconsulting.flicko.model

import com.farahaniconsulting.flicko.dto.PhotoItemDTO
import com.google.gson.annotations.SerializedName

data class Item(
    val author: String?,
    @SerializedName("author_id")
    val authorId: String?,
    @SerializedName("date_taken")
    val dateTaken: String?,
    val description: String?,
    val link: String?,
    val media: Media?,
    val published: String?,
    val tags: String?,
    val title: String?
) {
    fun toDTO() = PhotoItemDTO(
        imageUrl = link,
        title = title,
        description = description,
        author = author
    )
}