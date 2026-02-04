package com.bkseducate.securityapp.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.bkseducate.securityapp.application.dto.CountryDTO;
import com.bkseducate.securityapp.infrastructure.persistence.entity.CountryEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CountryMapper {
    CountryDTO toDTO(CountryEntity countryEntity);

    CountryEntity toEntity(CountryDTO countryDTO);

    
    
} 
 