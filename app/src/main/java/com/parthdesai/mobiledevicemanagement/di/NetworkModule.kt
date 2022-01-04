package com.parthdesai.mobiledevicemanagement.di

import com.google.gson.GsonBuilder
import com.parthdesai.mobiledevicemanagement.data.DeviceService
import com.parthdesai.mobiledevicemanagement.data.model.DeviceDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideDeviceMapper(): DeviceDtoMapper {
        return DeviceDtoMapper()
    }

    @Singleton
    @Provides
    fun provideDeviceService(): DeviceService {
        return Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/parth1493/2631d83f5cf7f93858ed3d1be5b06022/raw/a0cbb6310f6fa9608010b499810fc3c900089a33/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(DeviceService::class.java)
    }
}