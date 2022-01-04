package com.parthdesai.mobiledevicemanagement.data

import com.parthdesai.mobiledevicemanagement.data.response.DevicesResponse
import retrofit2.http.GET

interface DeviceService {

    @GET("deviceList")
    suspend fun getDevices(): DevicesResponse
}