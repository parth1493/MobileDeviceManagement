package com.parthdesai.mobiledevicemanagement.di

import com.parthdesai.mobiledevicemanagement.cache.DeviceDao
import com.parthdesai.mobiledevicemanagement.cache.model.DeviceEntityMapper
import com.parthdesai.mobiledevicemanagement.data.DeviceService
import com.parthdesai.mobiledevicemanagement.data.model.DeviceDtoMapper
import com.parthdesai.mobiledevicemanagement.interactors.FetchDevices
import com.parthdesai.mobiledevicemanagement.interactors.SearchDevices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object InteractorModule {

    @ViewModelScoped
    @Provides
    fun provideFetchDevices(
        deviceDao : DeviceDao,
        deviceService: DeviceService,
        entityMapper: DeviceEntityMapper,
        dtoMapper: DeviceDtoMapper
    ): FetchDevices {
        return FetchDevices(
            deviceService = deviceService,
            deviceDao = deviceDao,
            entityMapper = entityMapper,
            dtoMapper = dtoMapper,
        )
    }

    @ViewModelScoped
    @Provides
    fun provideSearchDevices(
        deviceDao : DeviceDao,
        entityMapper: DeviceEntityMapper,
    ): SearchDevices {
        return SearchDevices(
            deviceDao = deviceDao,
            entityMapper = entityMapper
        )
    }
}