package com.parthdesai.mobiledevicemanagement.interactors.device_list


import com.parthdesai.mobiledevicemanagement.cache.AppDatabaseFake
import com.parthdesai.mobiledevicemanagement.cache.DeviceDao
import com.parthdesai.mobiledevicemanagement.cache.DeviceDaoFake
import com.parthdesai.mobiledevicemanagement.cache.model.DeviceEntityMapper
import com.parthdesai.mobiledevicemanagement.domain.model.Device
import com.parthdesai.mobiledevicemanagement.interactors.SearchDevices
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SearchDevicesTest {

    private val appDatabase = AppDatabaseFake()
    private val DUMMY_QUERY = "Android" // can be anything

    private lateinit var searchDevices: SearchDevices

    // Dependencies
    private lateinit var deviceDao: DeviceDao
    private val entityMapper = DeviceEntityMapper()

    @BeforeEach
    private fun setup() {

        deviceDao = DeviceDaoFake(appDatabaseFake = appDatabase)

        searchDevices = SearchDevices(
            deviceDao = deviceDao,
            entityMapper = entityMapper
        )
    }

    @Test
    fun searchDevices_emitDevicesFromCache(): Unit = runBlocking {

        val flowItems = searchDevices.execute(DUMMY_QUERY).toList()

        // first emission should be `loading`
        assert(flowItems[0].loading)

        // Second emission should be the list of devices
        val devices = flowItems[1].data
        assert(devices?.size?: 0 > 0)

        // confirm they are actually Device objects
        assert(devices?.get(index = 0) is Device)

        assert(!flowItems[1].loading) // loading should be false now
    }
}