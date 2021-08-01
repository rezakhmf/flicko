package com.farahaniconsulting.flicko.ui.photocollection

import com.farahaniconsulting.flicko.dto.PhotoItemDTO
import com.farahaniconsulting.flicko.ui.data.PageErrorState

interface PhotoCollectionContract {

    data class ViewState(
        val isLoading: Boolean = false,
        val activityData: List<PhotoItemDTO> = listOf(),
        val errorState: PageErrorState? = null
    )

    data class DetailsViewState(
        val detailsData: PhotoItemDTO,
        val errorState: PageErrorState? = null
    )
}