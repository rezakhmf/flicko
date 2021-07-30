package com.farahaniconsulting.flicko.data.repository.local

import com.farahaniconsulting.flicko.data.repository.PhotoCollectionRepository
import com.farahaniconsulting.flicko.model.PhotoCollection

import com.google.gson.Gson
import io.reactivex.Single

class PhotoCollectionLocalRepository() : PhotoCollectionRepository {

    override fun getPhotoCollection(searchTerm: String?): Single<PhotoCollection?> {

        var result: PhotoCollection? = null
        searchTerm?.let {
            result = Gson().fromJson(it, PhotoCollection::class.java)
        }

        return Single.just(result)
    }
}
