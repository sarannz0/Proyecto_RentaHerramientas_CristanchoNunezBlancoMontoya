package com.bkseducate.securityapp.application.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.domain.model.City;
import com.bkseducate.securityapp.infrastructure.persistence.entity.CityEntity;

@Component
public class CityMapper {
    @Autowired
    private CountryMapper countryMapper;

    public City toDomain(CityEntity cityEntity) {
        if (cityEntity == null) return null;
        return City.recreate(
            cityEntity.getId(),
            cityEntity.getName(),
            countryMapper.toDomain(cityEntity.getCountry())
        );
    }

    public CityEntity toEntity(City city) {
        if (city == null) return null;
        CityEntity entity = new CityEntity();
        entity.setId(city.getId());
        entity.setName(city.getName());
        entity.setCountry(countryMapper.toEntity(city.getCountry()));
        return entity;
    };
}