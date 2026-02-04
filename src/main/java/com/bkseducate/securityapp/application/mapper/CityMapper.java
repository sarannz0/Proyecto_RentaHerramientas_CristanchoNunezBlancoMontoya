package com.bkseducate.securityapp.application.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.bkseducate.securityapp.application.dto.CityDTO;
import com.bkseducate.securityapp.infrastructure.persistence.entity.CityEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {CountryMapper.class})
public interface CityMapper {

    CityDTO toDTO(CityEntity cityEntity);

    CityEntity toEntity(CityDTO cityDTO);
}