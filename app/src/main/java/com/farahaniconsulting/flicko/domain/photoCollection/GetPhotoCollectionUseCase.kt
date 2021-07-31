package com.farahaniconsulting.flicko.domain.photoCollection

import com.farahaniconsulting.flicko.data.repository.PhotoCollectionRepository
import com.farahaniconsulting.flicko.domain.base.UseCase
import com.farahaniconsulting.flicko.dto.PhotoItemDTO
import io.reactivex.Scheduler
import io.reactivex.Single

class GetPhotoCollectionUseCase(
    private val repository: PhotoCollectionRepository,
    backgroundScheduler: Scheduler
) :
    UseCase<GetPhotoCollectionUseCase.RequestValues, GetPhotoCollectionUseCase.ResponseValue>(backgroundScheduler) {

    override fun executeUseCase(requestValues: RequestValues): Single<ResponseValue> =
         repository.getPhotoCollection(requestValues.searchItem).map { photoCollection ->
             photoCollection.items?.let { item ->
                 ResponseValue(item.map {
                     it.toDTO().apply {  }
                 }.toList())
             }
        }

    class RequestValues(val searchItem: String) : UseCase.RequestValues
    class ResponseValue(val photoItem: List<PhotoItemDTO>) : UseCase.ResponseValue

}