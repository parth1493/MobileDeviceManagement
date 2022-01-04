package com.parthdesai.mobiledevicemanagement.data.model

import com.google.gson.annotations.SerializedName

data class DeviceDto(
    @SerializedName("id")
    var id:String,
    @SerializedName("type")
    var type:String? = null,
    @SerializedName("price")
    var price:Double? = null,
    @SerializedName("currency")
    var currency:String? = null,
    @SerializedName("is_favorite")
    var isFavorite:Boolean? = null,
    @SerializedName("image_url")
    var imageURL:String? = null,
    @SerializedName("title")
    var title:String? = null,
    @SerializedName("description")
    var description:String? = null
)
