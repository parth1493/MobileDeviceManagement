package com.parthdesai.mobiledevicemanagement.cache.model

import com.parthdesai.mobiledevicemanagement.domain.EntityMapper
import com.parthdesai.mobiledevicemanagement.domain.model.Device

class DeviceEntityMapper : EntityMapper<DeviceEntity, Device> {

    override fun mapFromDomainModel(domainModel: Device): DeviceEntity {
        return DeviceEntity(
            id = domainModel.id,
            type = domainModel.type,
            price = domainModel.price,
            currency = domainModel.currency,
            isFavorite = domainModel.isFavorite,
            imageURL = domainModel.imageURL,
            title = domainModel.title,
            description = domainModel.description
        )
    }

    override fun mapToDomain(deviceEntity: DeviceEntity): Device {
        return Device(
            id = deviceEntity.id,
            type = deviceEntity.type,
            price = deviceEntity.price,
            currency = deviceEntity.currency,
            isFavorite = deviceEntity.isFavorite,
            imageURL = deviceEntity.imageURL,
            title = deviceEntity.title,
            description = deviceEntity.description
        )
    }

    fun fromEntityList(initial: List<DeviceEntity>): List<Device>{
        return initial.map { mapToDomain(it) }
    }

    fun toEntityList(initial: List<Device>): List<DeviceEntity>{
        return initial.map { mapFromDomainModel(it) }
    }
}