package com.farahaniconsulting.flicko.data.network

import com.farahaniconsulting.flicko.model.PhotoCollection
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("http://api.flickr.com/services/feeds/photos_public.gne?nojsoncallback=1&tagmode=any&format=json")
    fun getImages(): Single<PhotoCollection?>
}