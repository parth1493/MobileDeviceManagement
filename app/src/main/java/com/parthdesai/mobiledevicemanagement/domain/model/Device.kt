package com.parthdesai.mobiledevicemanagement.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Device(
    val id:String,
    val type:String? = null,
    val price:Double? = null,
    val currency:String? = null,
    val isFavorite:Boolean? = null,
    val imageURL:String? = null,
    val title:String? = null,
    val description:String? = null
) : Parcelable
