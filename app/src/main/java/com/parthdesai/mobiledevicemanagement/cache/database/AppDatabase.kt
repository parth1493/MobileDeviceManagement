package com.parthdesai.mobiledevicemanagement.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.parthdesai.mobiledevicemanagement.cache.DeviceDao
import com.parthdesai.mobiledevicemanagement.cache.model.DeviceEntity

@Database(entities = [DeviceEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun deviceDao(): DeviceDao

    companion object{
        val DATABASE_NAME: String = "device_db"
    }
}