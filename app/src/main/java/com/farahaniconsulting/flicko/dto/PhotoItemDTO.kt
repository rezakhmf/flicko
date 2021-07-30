package com.farahaniconsulting.flicko.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoItemDTO(val imageUrl: String?,
                            val title: String?,
                            val description: String?,
                            val imageWidth: Int? = 0,
                            val imageHeight: Int?= 0,
                           val author: String?
                            ) : Parcelable