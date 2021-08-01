package com.farahaniconsluting.flicko.ui.photocollection

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.farahaniconsluting.flicko.helper.TestHelpers
import com.farahaniconsluting.flicko.helper.any
import com.farahaniconsluting.flicko.helper.lambdaMock
import com.farahaniconsulting.flicko.domain.photoCollection.GetPhotoCollectionUseCase
import com.farahaniconsulting.flicko.model.PhotoCollection
import com.farahaniconsulting.flicko.ui.photocollection.PhotoCollectionContract
import com.farahaniconsulting.flicko.ui.photocollection.PhotoCollectionViewModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.*

class PhotoCollectionViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var photoCollectionUseCase: GetPhotoCollectionUseCase

    private lateinit var viewModel: PhotoCollectionViewModel

    private val viewStateObserver: Observer<PhotoCollectionContract.ViewState> = lambdaMock()

    @Captor
    private lateinit var viewStateCaptor: ArgumentCaptor<PhotoCollectionContract.ViewState>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    private fun initViewModel() {
        viewModel = PhotoCollectionViewModel(
            photoCollectionUseCase,
            Schedulers.trampoline()
        )
        viewModel.viewState.observeForever(viewStateObserver)
    }

    @Test
    fun `when api success should show photos`() {
        // Given
        Mockito.`when`(photoCollectionUseCase.run(any()))
            .thenReturn(getDummyPhotoCollection())
        initViewModel()
        // When
        viewModel.fetchPhotoCollection("olympic")
        // Then
        with(viewStateCaptor) {
            Mockito.verify(viewStateObserver, Mockito.times(2)).onChanged(capture())
            Assert.assertFalse(value.isLoading)
            Assert.assertNotNull(value.activityData)
            Assert.assertNull(value.errorState)
        }
    }

    @After
    fun tearDown() {
        viewModel.viewState.removeObserver(viewStateObserver)
    }

    private fun getDummyPhotoCollection(): Single<GetPhotoCollectionUseCase.ResponseValue> {
        val data = TestHelpers.loadData(
            "json/photoCollection_whenSuccess.json",
            PhotoCollection::class.java
        )
        val results = data?.items?.let { item ->
           item.map {
                it.toDTO().apply { }
            }.toList()
        }

        return Single.just(GetPhotoCollectionUseCase.ResponseValue(results!!))
    }
}