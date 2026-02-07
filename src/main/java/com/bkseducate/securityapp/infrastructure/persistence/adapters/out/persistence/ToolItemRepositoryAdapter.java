package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.domain.exceptions.UserNotFoundException;
import com.bkseducate.securityapp.domain.model.ToolItem;
import com.bkseducate.securityapp.domain.ports.ToolItemRepository;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.ToolItemMapper;
import com.bkseducate.securityapp.infrastructure.persistence.repository.ToolItemJpaRepository;

import jakarta.persistence.EntityNotFoundException;

@Component
public class ToolItemRepositoryAdapter implements ToolItemRepository{

    private final ToolItemJpaRepository jpaRepository;
    private final ToolItemMapper toolItemMapper;

    public ToolItemRepositoryAdapter(
        ToolItemJpaRepository jpaRepository,
        ToolItemMapper toolItemMapper
    ) {
        this.jpaRepository = jpaRepository;
        this.toolItemMapper = toolItemMapper;
    }

    @Override
    public List<ToolItem> findAll() {
        return jpaRepository.findAll().stream().map(toolItemMapper::toDomain).toList();
    }

    @Override
    public Optional<ToolItem> findById(UUID id) {
        return jpaRepository.findById(id).map(toolItemMapper::toDomain);
    }

    @Override
    public ToolItem save(ToolItem tool) {
        return toolItemMapper.toDomain(jpaRepository.save(toolItemMapper.toEntity(tool)));
    }

    @Override
    public ToolItem update(UUID id, ToolItem tool) {
        jpaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar el usuario con ID "+id));

            ToolItem updatedTool =  ToolItem.recreate(
                id,
                tool.getToolCatalog(),
                tool.getStatus(),
                tool.getAvaiable()
            );   
        jpaRepository.save(toolItemMapper.toEntity(updatedTool));
        return updatedTool;
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("No se pudo encontrar el usuario con ID "+id));
        jpaRepository.deleteById(id);
    }
}


