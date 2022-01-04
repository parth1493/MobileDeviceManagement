package com.parthdesai.mobiledevicemanagement.cache

import com.parthdesai.mobiledevicemanagement.cache.model.DeviceEntity

class DeviceDaoFake(
    private val appDatabaseFake: AppDatabaseFake
): DeviceDao {
    override suspend fun insertDevices(devices: List<DeviceEntity>): LongArray {
        appDatabaseFake.devices.addAll(devices)
        return longArrayOf(1)
    }

    override suspend fun deleteAllDevices() {
        appDatabaseFake.devices.clear()
    }

    override suspend fun searchDevices(query: String): List<DeviceEntity> {
        return appDatabaseFake.devices
    }

    override suspend fun getAllDevices(): List<DeviceEntity> {
        return appDatabaseFake.devices
    }
}