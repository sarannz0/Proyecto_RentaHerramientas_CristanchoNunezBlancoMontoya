package com.bkseducate.securityapp.infrastructure.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bkseducate.securityapp.infrastructure.persistence.entity.AddressEntity;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, UUID>{
    
}
