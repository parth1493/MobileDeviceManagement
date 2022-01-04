package com.parthdesai.mobiledevicemanagement.di

import androidx.room.Room
import com.parthdesai.mobiledevicemanagement.cache.DeviceDao
import com.parthdesai.mobiledevicemanagement.cache.database.AppDatabase
import com.parthdesai.mobiledevicemanagement.cache.model.DeviceEntityMapper
import com.parthdesai.mobiledevicemanagement.presentation.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDb(app: BaseApplication): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDeviceDao(db: AppDatabase): DeviceDao {
        return db.deviceDao()
    }

    @Singleton
    @Provides
    fun provideCacheDeviceMapper(): DeviceEntityMapper{
        return DeviceEntityMapper()
    }
}