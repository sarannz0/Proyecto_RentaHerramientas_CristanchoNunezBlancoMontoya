package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.domain.model.Country;
import com.bkseducate.securityapp.domain.ports.CountryRepositoryPort;
import com.bkseducate.securityapp.infrastructure.persistence.entity.CountryEntity;
import com.bkseducate.securityapp.infrastructure.persistence.repository.CountryJpaRepository;

@Component
public class CountryRepositoryAdapter implements CountryRepositoryPort{

    private final CountryJpaRepository jpaRepository;

    public CountryRepositoryAdapter(
        CountryJpaRepository jpaRepository
    ) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Country save(Country country) {
      return toDomain(jpaRepository.save(toEntity(country)));
    }

    @Override
    public Optional<Country> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Country> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Optional<Country> findByIsocode(String isocode) {
        return jpaRepository.findByIsocode(isocode).map(this::toDomain);
    }

    private Country toDomain(CountryEntity entity) {
        return Country.create(entity.getName(), entity.getIsocode());
    }

    private CountryEntity toEntity(Country country){
        return new CountryEntity(
            country.getId(),
            country.getName(),
            country.getIsocode()
        );
    }
    
}
