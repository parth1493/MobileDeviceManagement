package com.parthdesai.mobiledevicemanagement.domain

interface EntityMapper<Dto, DomainModel> {

    fun mapToDomain(dto: Dto): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): Dto
}