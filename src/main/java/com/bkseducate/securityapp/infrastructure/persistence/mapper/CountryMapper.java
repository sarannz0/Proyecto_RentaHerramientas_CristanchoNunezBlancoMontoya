package com.bkseducate.securityapp.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.domain.model.Country;
import com.bkseducate.securityapp.infrastructure.persistence.entity.CountryEntity;

@Component
public class CountryMapper {

    public CountryEntity toEntity(Country country) {
        CountryEntity entity = new CountryEntity();
        entity.setId(country.getId());
        entity.setName(country.getName());
        entity.setIsocode(country.getIsocode());
        return entity;
    }

    public Country toDomain(CountryEntity countryEntity) {
        return Country.recreate(
            countryEntity.getId(),
            countryEntity.getName(),
            countryEntity.getIsocode()
        );
    }
    
} 
 