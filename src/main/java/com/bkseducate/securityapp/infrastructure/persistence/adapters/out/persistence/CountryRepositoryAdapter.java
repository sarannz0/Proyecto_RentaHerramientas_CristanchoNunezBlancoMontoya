package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.application.mapper.CountryMapper;
import com.bkseducate.securityapp.domain.model.Country;
import com.bkseducate.securityapp.domain.ports.CountryRepositoryPort;
import com.bkseducate.securityapp.infrastructure.persistence.repository.CountryJpaRepository;

@Component
public class CountryRepositoryAdapter implements CountryRepositoryPort{

    private final CountryJpaRepository jpaRepository;
    private final CountryMapper countryMapper;

    public CountryRepositoryAdapter(
        CountryJpaRepository jpaRepository,
        CountryMapper countryMapper
    ) {
        this.countryMapper = countryMapper;
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Country save(Country country) {
      return countryMapper.toDomain(jpaRepository.save(countryMapper.toEntity(country)));
    }

    @Override
    public Optional<Country> findById(UUID id) {
        return jpaRepository.findById(id).map(countryMapper::toDomain);
    }

    @Override
    public List<Country> findAll() {
        return jpaRepository.findAll().stream().map(countryMapper::toDomain).toList();
    }

    @Override
    public Optional<Country> findByIsocode(String isocode) {
        return jpaRepository.findByIsocode(isocode).map(countryMapper::toDomain);
    }
    
}
