package com.farahaniconsulting.flicko.di.modules

import com.farahaniconsulting.flicko.data.network.ApiService
import com.farahaniconsulting.flicko.data.repository.PhotoCollectionRepository
import com.farahaniconsulting.flicko.data.repository.REPOSITORY_LOCAL
import com.farahaniconsulting.flicko.data.repository.REPOSITORY_REMOTE
import com.farahaniconsulting.flicko.data.repository.local.PhotoCollectionLocalRepository
import com.farahaniconsulting.flicko.data.repository.remote.PhotoCollectionRemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(REPOSITORY_REMOTE)
    fun providePhotoCollectionRepositoryRemoteRepository(apiService: ApiService) : PhotoCollectionRepository =
        PhotoCollectionRemoteRepository(apiService)

    @Provides
    @Singleton
    @Named(REPOSITORY_LOCAL)
    fun providePhotoCollectionRepositoryLocalRepository() : PhotoCollectionRepository =
        PhotoCollectionLocalRepository()
}