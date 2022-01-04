package com.parthdesai.mobiledevicemanagement.repository

import com.parthdesai.mobiledevicemanagement.data.DeviceService
import com.parthdesai.mobiledevicemanagement.data.model.DeviceDtoMapper
import com.parthdesai.mobiledevicemanagement.domain.model.Device

class DeviceRepositoryImpl(
    private val deviceService: DeviceService,
    private val mapper: DeviceDtoMapper
):DeviceRepository {
    override suspend fun getDevices(): List<Device> {
        return mapper.toDomainList(deviceService.getDevices().devices)
    }
}