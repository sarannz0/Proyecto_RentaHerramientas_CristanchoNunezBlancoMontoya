package com.bkseducate.securityapp.infrastructure.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bkseducate.securityapp.infrastructure.persistence.entity.ToolReportEntity;

@Repository
public interface ToolReportJpaRepository extends JpaRepository<ToolReportEntity, UUID> {
    
}
