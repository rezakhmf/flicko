package com.farahaniconsulting.flicko.data.repository

import com.farahaniconsulting.flicko.model.PhotoCollection
import io.reactivex.Single

const val REPOSITORY_LOCAL = "repositoryLocal"
const val REPOSITORY_REMOTE = "repositoryRemote"

interface PhotoCollectionRepository {
    fun getPhotoCollection(searchTerm: String?): Single<PhotoCollection?>
}