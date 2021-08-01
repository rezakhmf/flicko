package com.farahaniconsulting.flicko.ui.photocollection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.farahaniconsulting.flicko.di.modules.OBSERVER_ON
import com.farahaniconsulting.flicko.domain.photoCollection.GetPhotoCollectionUseCase
import com.farahaniconsulting.flicko.dto.PhotoItemDTO
import com.farahaniconsulting.flicko.ui.base.BaseViewModel
import com.farahaniconsulting.flicko.ui.data.PageErrorState
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableSingleObserver
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Named

class PhotoCollectionViewModel(
    private val getPhotoCollectionUseCase: GetPhotoCollectionUseCase,
    @Named(OBSERVER_ON) private val observerOn: Scheduler
): BaseViewModel() {

    private val mutableViewState: MutableLiveData<PhotoCollectionContract.ViewState> by lazy {
        MutableLiveData<PhotoCollectionContract.ViewState>()
    }

    private val mutablePhotoCollection: MutableLiveData<PhotoCollectionContract.DetailsViewState> by lazy {
        MutableLiveData<PhotoCollectionContract.DetailsViewState>()
    }

    val viewState: LiveData<PhotoCollectionContract.ViewState>
        get() = mutableViewState

    val detailsView: LiveData<PhotoCollectionContract.DetailsViewState>
        get()= mutablePhotoCollection

    fun fetchPhotoCollection(searchItem: String) {

        mutableViewState.value  = PhotoCollectionContract.ViewState(
            isLoading = true, errorState = null
        )

        addDisposable(
            getPhotoCollectionUseCase.run(GetPhotoCollectionUseCase.RequestValues(searchItem))
                .observeOn(observerOn)
                .subscribeWith(
                    object : DisposableSingleObserver<GetPhotoCollectionUseCase.ResponseValue>() {
                        override fun onSuccess(apiResponse: GetPhotoCollectionUseCase.ResponseValue) {
                            Timber.d("photo collection Items = ${apiResponse.photoItem}")
                            mutableViewState.value = PhotoCollectionContract.ViewState(
                                isLoading = false,
                                activityData = apiResponse.photoItem
                            )
                        }

                        override fun onError(error: Throwable) {
                            if (error is IOException) {
                                handleLoadingError(PageErrorState.NO_NETWORK)
                            } else if (error is HttpException) {
                                handleLoadingError(PageErrorState.SERVER_ERROR)
                            }
                            Timber.e(error)
                        }
                    }
                )
        )
    }

    fun photoTapped(photoItem: PhotoItemDTO) {
        mutablePhotoCollection.value = PhotoCollectionContract.DetailsViewState(
            detailsData = photoItem,
            errorState = null
        )
    }
    private fun handleLoadingError(errorState: PageErrorState) {
        mutableViewState.value = PhotoCollectionContract.ViewState(
            isLoading = false,
            errorState = errorState
        )
    }
}