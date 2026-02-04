package com.bkseducate.securityapp.application.mapper;

import org.mapstruct.Mapper;

import com.bkseducate.securityapp.application.dto.AddressDTO;
import com.bkseducate.securityapp.infrastructure.persistence.entity.AddressEntity;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDTO toDTO(AddressEntity addressEntity);

    AddressEntity toEntity(AddressDTO addressDTO);
}