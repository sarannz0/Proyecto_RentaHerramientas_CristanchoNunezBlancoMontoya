package com.bkseducate.securityapp.application.mapper;

import org.mapstruct.Mapper;

import com.bkseducate.securityapp.application.dto.AddressDTO;
import com.bkseducate.securityapp.domain.model.Address;
import com.bkseducate.securityapp.infrastructure.persistence.entity.AddressEntity;

@Mapper(componentModel = "spring",
    uses = {CityMapper.class}
)
public interface AddressMapper {

    AddressDTO toDTO(AddressEntity addressEntity);

    Address toDomain(AddressEntity addressEntity);

    AddressEntity DtoToEntity(AddressDTO addressDTO);

    AddressEntity toEntity(Address address);
    
}