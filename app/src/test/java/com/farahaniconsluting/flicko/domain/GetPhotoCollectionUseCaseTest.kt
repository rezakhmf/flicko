package com.farahaniconsluting.flicko.domain

import com.farahaniconsluting.flicko.helper.TestHelpers
import com.farahaniconsulting.flicko.data.repository.remote.PhotoCollectionRemoteRepository
import com.farahaniconsulting.flicko.domain.photoCollection.GetPhotoCollectionUseCase
import com.farahaniconsulting.flicko.model.PhotoCollection
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetPhotoCollectionUseCaseTest {

    @Mock
    private lateinit var repository: PhotoCollectionRemoteRepository

    private lateinit var useCase: GetPhotoCollectionUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = GetPhotoCollectionUseCase(repository, Schedulers.trampoline())
    }

    @Test
    fun `should return photo collection launches when api success`() {

        Mockito.`when`(repository.getPhotoCollection("olympic"))
            .thenReturn(
                Single.just(
                    TestHelpers.loadData(
                        "json/photoCollection_whenSuccess.json",
                        PhotoCollection::class.java
                    )
                )
            )

        val observable = useCase.run(GetPhotoCollectionUseCase.RequestValues("olympic"))
        val testObserver = TestObserver<GetPhotoCollectionUseCase.ResponseValue>()
        observable.subscribeWith(testObserver)

        // then verify
        testObserver.assertSubscribed()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        Assert.assertEquals(
            "result item size",
            20,
            testObserver.values()[0].photoItem.size
        )
    }
}