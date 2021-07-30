package com.farahaniconsulting.flicko.di.modules

import androidx.lifecycle.ViewModel
import com.farahaniconsulting.flicko.domain.photoCollection.GetPhotoCollectionUseCase
import com.farahaniconsulting.flicko.ui.photocollection.PhotoCollectionViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Provider

@Module
class ViewModelModule {

    @Provides
    fun providesViewModelFactory(
        viewModelMap: Map<Class<out ViewModel>,
                @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelFactory {
        return ViewModelFactory(viewModelMap)
    }

    @Provides
    @IntoMap
    @ViewModelKey(PhotoCollectionViewModel::class)
    fun providePhotoCollectionViewModel(
        getPhotoCollectionUseCase: GetPhotoCollectionUseCase,
        @Named(OBSERVER_ON) observerOn: Scheduler
    ): ViewModel {
        return PhotoCollectionViewModel(
            getPhotoCollectionUseCase, observerOn
        )
    }
}