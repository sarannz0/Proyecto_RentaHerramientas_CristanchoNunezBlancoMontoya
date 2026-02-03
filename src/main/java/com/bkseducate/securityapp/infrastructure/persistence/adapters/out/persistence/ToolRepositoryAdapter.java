package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bkseducate.securityapp.domain.exceptions.UserNotFoundException;
import com.bkseducate.securityapp.domain.model.Tool;
import com.bkseducate.securityapp.domain.ports.ToolRepository;
import com.bkseducate.securityapp.infrastructure.persistence.entity.ToolEntity;
import com.bkseducate.securityapp.infrastructure.persistence.repository.ToolJpaRepository;

public class ToolRepositoryAdapter implements ToolRepository{

    private final ToolJpaRepository jpaRepository;

    public ToolRepositoryAdapter(ToolJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Tool> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Optional<Tool> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Tool save(Tool tool) {
        return toDomain(jpaRepository.save(toEntity(tool)));
    }

    @Override
    public Tool update(UUID id, Tool tool) {
        ToolEntity entity = jpaRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("No se pudo encontrar el usuario con ID "+id));
        Tool updatedTool =  Tool.reconstruct(
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

    private Tool toDomain(ToolEntity entity) {
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

    private ToolEntity toEntity(Tool tool) {
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


