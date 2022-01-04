package com.parthdesai.mobiledevicemanagement.di

import com.parthdesai.mobiledevicemanagement.data.DeviceService
import com.parthdesai.mobiledevicemanagement.data.model.DeviceDtoMapper
import com.parthdesai.mobiledevicemanagement.repository.DeviceRepository
import com.parthdesai.mobiledevicemanagement.repository.DeviceRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDeviceRepository(
        deviceService: DeviceService,
        deviceMapper: DeviceDtoMapper,
    ): DeviceRepository {
        return DeviceRepositoryImpl(
            deviceService = deviceService,
            mapper = deviceMapper
        )
    }
}