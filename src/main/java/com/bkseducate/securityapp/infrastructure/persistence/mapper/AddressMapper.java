package com.bkseducate.securityapp.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.domain.model.Address;
import com.bkseducate.securityapp.infrastructure.persistence.entity.AddressEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AddressMapper {

    private final CityMapper cityMapper;

    public Address toDomain(AddressEntity addressEntity) {
        if (addressEntity == null) return null;
        return Address.recreate(
            addressEntity.getId(),
            addressEntity.getAddress(),
            addressEntity.getPostal_code(),
            cityMapper.toDomain(addressEntity.getCity())
        );
    }
    
    public AddressEntity toEntity(Address address) {
        if (address == null) return null;
        AddressEntity entity = new AddressEntity();
        entity.setId(address.getId());
        entity.setAddress(address.getAddress());
        entity.setPostal_code(address.getPostalCode());
        entity.setCity(cityMapper.toEntity(address.getCity()));
        return entity;
    }
}