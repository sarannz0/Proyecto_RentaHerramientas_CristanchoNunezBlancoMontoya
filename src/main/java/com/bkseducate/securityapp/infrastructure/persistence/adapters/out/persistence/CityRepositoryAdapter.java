package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.application.mapper.CityMapper;
import com.bkseducate.securityapp.domain.model.City;
import com.bkseducate.securityapp.domain.ports.CityRepositoryPort;
import com.bkseducate.securityapp.infrastructure.persistence.repository.CityJpaRepository;

@Component
public class CityRepositoryAdapter implements CityRepositoryPort{
    
    private final CityJpaRepository jpaRepository;
    private final CityMapper cityMapper;

    public CityRepositoryAdapter(
        CityJpaRepository jpaRepository,
        CityMapper cityMapper
    ) {
        this.jpaRepository = jpaRepository;
        this.cityMapper = cityMapper;
    }

    @Override
    public List<City> findAll() {
         return jpaRepository.findAll().stream().map(cityMapper::toDomain).toList();
    }

    @Override
    public Optional<City> findById(UUID id) {
        return jpaRepository.findById(id).map(cityMapper::toDomain);
    }

    @Override
    public Optional<City> findByName(String name) {
        return jpaRepository.findByName(name).map(cityMapper::toDomain);
    }

    @Override
    public City save(City city) {
        return cityMapper.toDomain(jpaRepository.save(cityMapper.toEntity(city)));
    }
}