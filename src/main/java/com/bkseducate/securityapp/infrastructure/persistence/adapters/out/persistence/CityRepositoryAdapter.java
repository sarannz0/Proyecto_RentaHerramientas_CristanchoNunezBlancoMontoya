package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.domain.exceptions.UserNotFoundException;
import com.bkseducate.securityapp.domain.model.City;
import com.bkseducate.securityapp.domain.ports.CityRepository;
import com.bkseducate.securityapp.infrastructure.persistence.entity.CityEntity;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.CountryMapper;
import com.bkseducate.securityapp.infrastructure.persistence.repository.CityJpaRepository;

@Component
public class CityRepositoryAdapter implements CityRepository{
    
    private final CityJpaRepository jpaRepository;
    //private final CityMapper cityMapper;
    private final CountryMapper countryMapper;


    public CityRepositoryAdapter(
        CityJpaRepository jpaRepository,
        //@Lazy CityMapper cityMapper,
        CountryMapper countryMapper
    ) {
        this.jpaRepository = jpaRepository;
        //this.cityMapper = cityMapper;
        this.countryMapper = countryMapper;
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
    
    
    @Override
    public City update(UUID id, City city) {
        CityEntity entity = jpaRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("No se pudo encontrar el usuario con ID " + id));
        entity.setName(city.getName());
        entity.setCountry(countryMapper.toEntity(city.getCountry()));
        return toDomain(jpaRepository.save(entity));
    };

    private City toDomain(CityEntity cityEntity) {
        if (cityEntity == null) return null;
        return City.recreate(
            cityEntity.getId(),
            cityEntity.getName(),
            countryMapper.toDomain(cityEntity.getCountry())
        );
    }

    private CityEntity toEntity(City city) {
        if (city == null) return null;
        CityEntity entity = new CityEntity();
        entity.setId(city.getId());
        entity.setName(city.getName());
        entity.setCountry(countryMapper.toEntity(city.getCountry()));
        return entity;
    }
}