package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.domain.model.ToolCatalog;
import com.bkseducate.securityapp.domain.ports.ToolCatalogRepository;
import com.bkseducate.securityapp.infrastructure.persistence.entity.ToolCatalogEntity;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.SupplierMapper;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.ToolCatalogMapper;
import com.bkseducate.securityapp.infrastructure.persistence.repository.ToolCatalogJpaRepository;

import jakarta.persistence.EntityNotFoundException;

@Component
public class ToolCatalogRepositoryAdapter implements ToolCatalogRepository {

    private final ToolCatalogJpaRepository jpaRepository;
    private final ToolCatalogMapper toolCatalogMapper;
    private final SupplierMapper supplierMapper;

    public ToolCatalogRepositoryAdapter(
        ToolCatalogJpaRepository jpaRepository,
        ToolCatalogMapper toolCatalogMapper,
        SupplierMapper supplierMapper
    ) {
        this.supplierMapper = supplierMapper;
        this.jpaRepository = jpaRepository;
        this.toolCatalogMapper = toolCatalogMapper;
    }

    @Override
    public List<ToolCatalog> findAll() {
        return jpaRepository.findAll().stream().map(toolCatalogMapper::toDomain).toList();
    }

    @Override
    public Optional<ToolCatalog> findById(UUID id) {
        return jpaRepository.findById(id).map(toolCatalogMapper::toDomain);
    }

    @Override
    public ToolCatalog save(ToolCatalog tool) {
        return toolCatalogMapper.toDomain(jpaRepository.save(toolCatalogMapper.toEntity(tool)));
    }

    @Override
    public ToolCatalog update(UUID id, ToolCatalog tool) {
        ToolCatalogEntity entity = jpaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar el usuario con ID "+id));
        entity.setId(id);
        entity.setSupplier(supplierMapper.toEntity(tool.getSupplier()));
        entity.setName(tool.getName());
        entity.setPrice(tool.getPrice());
        entity.setStatus(tool.getStatus());
        entity.setDescription(tool.getDescription());
        entity.setImageUrl(tool.getImageUrl());
        return toolCatalogMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    }
    
}
