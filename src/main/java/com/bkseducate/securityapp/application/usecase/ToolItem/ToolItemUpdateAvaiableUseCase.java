package com.bkseducate.securityapp.application.usecase.ToolItem;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.domain.model.ToolItem;
import com.bkseducate.securityapp.infrastructure.persistence.entity.ToolItemEntity;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.ToolCatalogMapper;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.ToolItemMapper;
import com.bkseducate.securityapp.infrastructure.persistence.repository.ToolItemJpaRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToolItemUpdateAvaiableUseCase {
    
    private final ToolItemJpaRepository toolItemJpaRepository;
    private final ToolCatalogMapper toolCatalogMapper;
    private final ToolItemMapper toolItemMapper;

    public void execute(UUID itemId, Boolean avaiable, UUID supplierId) {
        ToolItemEntity toolItemEntity = toolItemJpaRepository.findById(itemId)
            .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar el ITEM con ID " + itemId));

        if (!toolItemEntity.getToolCatalogEntity().getSupplier().getUserId().equals(supplierId)) 
            throw new RuntimeException("No tienes permiso para acceder a este recurso");

        toolItemJpaRepository.save(toolItemMapper.toEntity(
            ToolItem.recreate(
                toolItemEntity.getId(),
                toolCatalogMapper.toDomain(toolItemEntity.getToolCatalogEntity()),
                toolItemEntity.getStatus(),
                avaiable
            )
        ));
    }
}
