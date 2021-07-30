package com.farahaniconsulting.flicko.model

import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("m")
    val source: String?
)