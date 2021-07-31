package com.farahaniconsulting.flicko.data.network

import com.farahaniconsulting.flicko.model.PhotoCollection
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("services/feeds/photos_public.gne?nojsoncallback=1&tagmode=any&format=json")
    fun getImages(@Query(value = "tags") searchItem: String): Single<PhotoCollection?>
}