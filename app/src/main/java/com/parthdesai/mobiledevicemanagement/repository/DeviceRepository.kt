package com.parthdesai.mobiledevicemanagement.repository

import com.parthdesai.mobiledevicemanagement.domain.model.Device

interface DeviceRepository {

    suspend fun getDevices(): List<Device>
}