package com.farahaniconsulting.flicko.di.modules

import com.farahaniconsulting.flicko.data.repository.PhotoCollectionRepository
import com.farahaniconsulting.flicko.data.repository.REPOSITORY_LOCAL
import com.farahaniconsulting.flicko.domain.photoCollection.GetPhotoCollectionUseCase
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetUpComingLaunchesUC(
        @Named(REPOSITORY_LOCAL) repository: PhotoCollectionRepository,
        @Named(SUBSCRIBER_ON) backgroundScheduler: Scheduler
    ): GetPhotoCollectionUseCase =
        GetPhotoCollectionUseCase(
            repository,
            backgroundScheduler)

}