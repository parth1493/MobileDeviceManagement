package com.parthdesai.mobiledevicemanagement.data.model

import com.parthdesai.mobiledevicemanagement.domain.EntityMapper
import com.parthdesai.mobiledevicemanagement.domain.model.Device

class DeviceDtoMapper : EntityMapper<DeviceDto, Device> {

    override fun mapToDomain(dto: DeviceDto): Device {
        return Device(
            id = dto.id,
            type = dto.type,
            price = dto.price,
            currency = dto.currency,
            isFavorite = dto.isFavorite,
            imageURL = dto.imageURL,
            title = dto.title,
            description = dto.description
        )
    }

    fun toDomainList(initial: List<DeviceDto>): List<Device>{
        return initial.map { mapToDomain(it) }
    }

    override fun mapFromDomainModel(domainModel: Device): DeviceDto {
        TODO("Not yet implemented")
    }
}