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

    var searchTerm: String? = null

    override fun executeUseCase(requestValues: RequestValues): Single<ResponseValue> =
         repository.getPhotoCollection(searchTerm).map { photoCollection ->
             photoCollection.items?.let { item ->
                 ResponseValue(item.map {
                     it.toDTO().apply {  }
                 }.toList())
             }
        }

    class RequestValues : UseCase.RequestValues
    class ResponseValue(val photoItem: List<PhotoItemDTO>) : UseCase.ResponseValue

}