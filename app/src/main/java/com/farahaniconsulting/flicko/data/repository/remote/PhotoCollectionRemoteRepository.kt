package com.farahaniconsulting.flicko.data.repository.remote

import com.farahaniconsulting.flicko.data.network.ApiService
import com.farahaniconsulting.flicko.data.repository.PhotoCollectionRepository
import retrofit2.http.Query

class PhotoCollectionRemoteRepository(private val apiService: ApiService) : PhotoCollectionRepository {

    override fun getPhotoCollection(@Query(value = "tags") searchTerm: String?) =
        apiService.getImages()
}