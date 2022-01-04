package com.parthdesai.mobiledevicemanagement.interactors

import com.parthdesai.mobiledevicemanagement.cache.DeviceDao
import com.parthdesai.mobiledevicemanagement.cache.model.DeviceEntityMapper
import com.parthdesai.mobiledevicemanagement.data.DeviceService
import com.parthdesai.mobiledevicemanagement.data.model.DeviceDtoMapper
import com.parthdesai.mobiledevicemanagement.domain.data.DataState
import com.parthdesai.mobiledevicemanagement.domain.model.Device
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow

class FetchDevices(
    private val deviceDao: DeviceDao,
    private val deviceService: DeviceService,
    private val entityMapper: DeviceEntityMapper,
    private val dtoMapper: DeviceDtoMapper
) {

    fun execute(
        isNetworkAvailable: Boolean,
    ): Flow<DataState<List<Device>>> = flow {
        try {
            //start with loding
            emit(DataState.loading())

            delay(2000)

            //do the network call
            if(isNetworkAvailable) {
                val devices = getDevicesFromNetwork()

            //insert into cache
            deviceDao.insertDevices(entityMapper.toEntityList(devices))
            }
            val cacheResult = deviceDao.getAllDevices()

            val list = entityMapper.fromEntityList(cacheResult)

            emit(DataState.success(list))

        }catch (e: Exception){
            emit(DataState.error<List<Device>>(e.message ?: "Unknown Error"))
        }
    }

    private suspend fun getDevicesFromNetwork(): List<Device> {
        return dtoMapper.toDomainList(
            deviceService.getDevices().devices
        )
    }
}