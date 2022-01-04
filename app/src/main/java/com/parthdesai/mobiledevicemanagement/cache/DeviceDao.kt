package com.parthdesai.mobiledevicemanagement.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.parthdesai.mobiledevicemanagement.cache.model.DeviceEntity

@Dao
interface DeviceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDevices(devices: List<DeviceEntity>): LongArray

    @Query("DELETE FROM devices")
    suspend fun deleteAllDevices()

    @Query(""" SELECT * FROM devices WHERE title LIKE '%' || :query || '%' """)
    suspend fun searchDevices(
        query: String
    ): List<DeviceEntity>

    @Query(" SELECT * FROM devices ")
    suspend fun getAllDevices(): List<DeviceEntity>
}