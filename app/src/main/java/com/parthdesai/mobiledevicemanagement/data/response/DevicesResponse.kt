package com.parthdesai.mobiledevicemanagement.data.response

import com.google.gson.annotations.SerializedName
import com.parthdesai.mobiledevicemanagement.data.model.DeviceDto

data class DevicesResponse(
    @SerializedName("devices")
    var devices: List<DeviceDto>
) {
}