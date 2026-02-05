package com.bkseducate.securityapp.infrastructure.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bkseducate.securityapp.infrastructure.persistence.entity.ToolCatalogEntity;

public interface ToolCatalogJpaRepository extends JpaRepository<ToolCatalogEntity, UUID>{
    
}
