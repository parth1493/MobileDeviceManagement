package com.parthdesai.mobiledevicemanagement.interactors

import com.parthdesai.mobiledevicemanagement.cache.DeviceDao
import com.parthdesai.mobiledevicemanagement.cache.model.DeviceEntityMapper
import com.parthdesai.mobiledevicemanagement.domain.data.DataState
import com.parthdesai.mobiledevicemanagement.domain.model.Device
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchDevices(
    private val deviceDao: DeviceDao,
    private val entityMapper: DeviceEntityMapper,
) {
    fun execute(
        query: String
    ): Flow<DataState<List<Device>>> = flow {
        try {
            emit(DataState.loading())

            //test with delay
           // delay(1000)

            // query the cache
            val cacheResult = if (query.isBlank()){
                deviceDao.getAllDevices()
            }
            else{
                deviceDao.searchDevices(
                    query = query
                )
            }

            // emit from cache
            val list = entityMapper.fromEntityList(cacheResult)
            emit(DataState.success(list))

        }catch (e: Exception){
            emit(DataState.error<List<Device>>(e.message?: "Unknown Error"))
        }
    }

}