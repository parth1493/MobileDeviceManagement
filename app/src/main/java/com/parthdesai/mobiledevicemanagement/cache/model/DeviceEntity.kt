package com.parthdesai.mobiledevicemanagement.cache.model

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "devices")
data class DeviceEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id:String,

    @Nullable
    @ColumnInfo(name = "type")
    val type:String? = null,

    @Nullable
    @ColumnInfo(name = "price")
    val price:Double? = null,

    @Nullable
    @ColumnInfo(name = "currency")
    val currency:String? = null,

    @Nullable
    @ColumnInfo(name = "is_favorite")
    val isFavorite:Boolean? = null,

    @Nullable
    @ColumnInfo(name = "image_url")
    val imageURL:String? = null,

    @Nullable
    @ColumnInfo(name = "title")
    val title:String? = null,

    @Nullable
    @ColumnInfo(name = "description")
    val description:String? = null
)