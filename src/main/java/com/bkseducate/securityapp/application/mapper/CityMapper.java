package com.bkseducate.securityapp.application.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.bkseducate.securityapp.application.dto.CityDTO;
import com.bkseducate.securityapp.domain.model.City;
import com.bkseducate.securityapp.infrastructure.persistence.entity.CityEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {CountryMapper.class})
public interface CityMapper {

    CityDTO toDTO(CityEntity cityEntity);

    CityEntity DtoToEntity(CityDTO cityDTO);

    City toDomain(CityEntity cityEntity);

    CityEntity toEntity(City city);
}