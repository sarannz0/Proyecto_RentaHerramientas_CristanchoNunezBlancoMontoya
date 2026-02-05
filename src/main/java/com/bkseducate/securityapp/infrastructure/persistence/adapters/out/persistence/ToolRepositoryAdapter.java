package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bkseducate.securityapp.domain.exceptions.UserNotFoundException;
import com.bkseducate.securityapp.domain.model.ToolCatalog;
import com.bkseducate.securityapp.domain.ports.ToolRepository;
import com.bkseducate.securityapp.infrastructure.persistence.entity.ToolEntity;
import com.bkseducate.securityapp.infrastructure.persistence.repository.ToolJpaRepository;

public class ToolRepositoryAdapter implements ToolRepository{

    private final ToolJpaRepository jpaRepository;

    public ToolRepositoryAdapter(ToolJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<ToolCatalog> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Optional<ToolCatalog> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public ToolCatalog save(ToolCatalog tool) {
        return toDomain(jpaRepository.save(toEntity(tool)));
    }

    @Override
    public ToolCatalog update(UUID id, ToolCatalog tool) {
        ToolEntity entity = jpaRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("No se pudo encontrar el usuario con ID "+id));
            ToolCatalog updatedTool =  Tool.reconstruct(
            entity.getId(),
            entity.getName(),
            entity.getStock(),
            entity.getPrice(),
            entity.getStatus(),
            entity.getDesciption(),
            entity.getImageUrl()
        );   
        jpaRepository.save(toEntity(updatedTool));
        return updatedTool;
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("No se pudo encontrar el usuario con ID "+id));
        jpaRepository.deleteById(id);
    }

    private ToolCatalog toDomain(ToolEntity entity) {
        return Tool.reconstruct(
            entity.getId(),
            entity.getName(),
            entity.getStock(),
            entity.getPrice(),
            entity.getStatus(),
            entity.getDesciption(),
            entity.getImageUrl()
        );
    }

    private ToolCatalog toEntity(ToolCatalog tool) {
        return new ToolEntity(
            tool.getId(),
            tool.getName(),
            tool.getStock(),
            tool.getPrice(),
            tool.getStatus(),
            tool.getDesciption(),
            tool.getImageUrl()
        );
    }
}


