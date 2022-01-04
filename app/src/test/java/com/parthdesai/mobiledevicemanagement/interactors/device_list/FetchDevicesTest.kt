package com.parthdesai.mobiledevicemanagement.interactors.device_list

import com.google.gson.GsonBuilder
import com.parthdesai.mobiledevicemanagement.cache.AppDatabaseFake
import com.parthdesai.mobiledevicemanagement.cache.DeviceDao
import com.parthdesai.mobiledevicemanagement.cache.DeviceDaoFake
import com.parthdesai.mobiledevicemanagement.cache.model.DeviceEntityMapper
import com.parthdesai.mobiledevicemanagement.data.DeviceService
import com.parthdesai.mobiledevicemanagement.data.model.DeviceDtoMapper
import com.parthdesai.mobiledevicemanagement.domain.model.Device
import com.parthdesai.mobiledevicemanagement.interactors.FetchDevices
import com.parthdesai.mobiledevicemanagement.network.data.MockWebServerResponses.deviceListResponse
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class FetchDevicesTest {

    private val appDatabase = AppDatabaseFake()
    private lateinit var mockWebServer: MockWebServer
    private lateinit var baseUrl: HttpUrl

    private lateinit var fetchDevices: FetchDevices

    // Dependencies
    private lateinit var deviceService: DeviceService
    private lateinit var deviceDao: DeviceDao
    private val entityMapper = DeviceEntityMapper()
    private val dtoMapper = DeviceDtoMapper()

    @BeforeEach
    private fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        baseUrl = mockWebServer.url("/raw/a0cbb6310f6fa9608010b499810fc3c900089a33/")
        deviceService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(DeviceService::class.java)

        deviceDao = DeviceDaoFake(appDatabaseFake = appDatabase)

        fetchDevices = FetchDevices(
            deviceDao = deviceDao,
            deviceService = deviceService,
            entityMapper = entityMapper,
            dtoMapper = dtoMapper
        )
    }

    @Test
    fun getDevicesFromNetwork_emitDevicesFromCache(): Unit = runBlocking {
        // condition the response
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(deviceListResponse)
        )

        // confirm the cache is empty to start
        assert(deviceDao.getAllDevices().isEmpty())

        val flowItems = fetchDevices.execute(true).toList()

        // confirm the cache is no longer empty
        assert(deviceDao.getAllDevices().isNotEmpty())

        // first emission should be `loading`
        assert(flowItems[0].loading)

        // Second emission should be the list of devices
        val devices = flowItems[1].data
        assert(devices?.size?: 0 > 0)

        // confirm they are actually Device objects
        assert(devices?.get(index = 0) is Device)

        assert(!flowItems[1].loading) // loading should be false now
    }

    /**
     * Simulate a bad request
     */
    @Test
    fun getDevicesFromNetwork_emitHttpError(): Unit = runBlocking {

        // condition the response
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .setBody("{}")
        )

        val flowItems = fetchDevices.execute(true).toList()

        // first emission should be `loading`
        assert(flowItems[0].loading)

        // Second emission should be the exception
        val error = flowItems[1].error
        assert(error != null)

        assert(!flowItems[1].loading) // loading should be false now
    }

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }

}