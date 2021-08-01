package com.farahaniconsluting.flicko.data.repository.remote

import com.farahaniconsluting.flicko.base.BaseTest
import com.farahaniconsulting.flicko.data.network.ApiService
import com.farahaniconsulting.flicko.data.repository.remote.PhotoCollectionRemoteRepository
import com.farahaniconsulting.flicko.model.PhotoCollection
import com.google.gson.GsonBuilder
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class PhotoCollectionRemoteRepositoryTest: BaseTest() {

    private val apiClient = OkHttpClient().newBuilder().build()
    private val mockApi: ApiService by lazy {
        Retrofit.Builder()
            .client(apiClient)
            .baseUrl(mockServer.url("/").toString())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private lateinit var repository: PhotoCollectionRemoteRepository

    @Before
    fun setUpMock() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `should return photo collection response when api success`() {
        repository = PhotoCollectionRemoteRepository(apiService = mockApi)

        this.mockHttpResponse("json/photoCollection_whenSuccess.json",HttpURLConnection.HTTP_OK)

        val observable = repository.getPhotoCollection("olympic")
        val testObserver = TestObserver<PhotoCollection>()
        observable.subscribeWith(testObserver)

        // then verify
        testObserver.assertSubscribed()
        testObserver.assertComplete()
        testObserver.assertNoErrors()

        Assert.assertEquals("result item size", 20, testObserver.values()[0].items!!.size)
    }

    override fun isMockServerEnabled(): Boolean = true
}