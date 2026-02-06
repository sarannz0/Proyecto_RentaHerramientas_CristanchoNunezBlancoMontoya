package com.bkseducate.securityapp.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bkseducate.securityapp.application.dto.AddressDTO;
import com.bkseducate.securityapp.domain.model.Address;
import com.bkseducate.securityapp.infrastructure.persistence.entity.AddressEntity;

@Mapper(componentModel = "spring",
    uses = {CityMapper.class}
)
public abstract class AddressMapper {
    protected CityMapper cityMapper;
    public abstract AddressEntity DtoToEntity(AddressDTO addressDTO);
    public abstract AddressDTO toDTO(AddressEntity addressEntity);

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