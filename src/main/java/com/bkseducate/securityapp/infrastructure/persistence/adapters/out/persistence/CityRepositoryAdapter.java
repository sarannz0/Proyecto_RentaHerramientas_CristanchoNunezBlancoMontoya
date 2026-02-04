package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.City;
import com.bkseducate.securityapp.domain.model.Country;
import com.bkseducate.securityapp.domain.ports.CityRepositoryPort;
import com.bkseducate.securityapp.infrastructure.persistence.entity.CityEntity;
import com.bkseducate.securityapp.infrastructure.persistence.entity.CountryEntity;
import com.bkseducate.securityapp.infrastructure.persistence.repository.CityJpaRepository;

public class CityRepositoryAdapter implements CityRepositoryPort{
    
    private final CityJpaRepository jpaRepository;

    public CityRepositoryAdapter(
        CityJpaRepository jpaRepository
    ) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<City> findAll() {
         return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Optional<City> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<City> findByName(String name) {
        return jpaRepository.findByName(name).map(this::toDomain);
    }

    @Override
    public City save(City city) {
        return toDomain(jpaRepository.save(toEntity(city)));
    }

     private City toDomain(CityEntity entity) {
        return City.create(entity.getName(), countryToDomain(entity.getCountry()));
    }

    private CityEntity toEntity(City city) {
        return new CityEntity(
            city.getId(),
            city.getName(),
            countrytoEntity(city.getCountry())
        );
    }

    private CountryEntity countrytoEntity(Country country){
        return new CountryEntity(
            country.getId(),
            country.getName(),
            country.getIsocode()
        );
    }

    private Country countryToDomain(CountryEntity entity) {
        return Country.create(entity.getName(), entity.getIsocode());
    }
}