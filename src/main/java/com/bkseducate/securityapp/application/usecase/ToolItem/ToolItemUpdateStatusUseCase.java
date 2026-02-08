package com.bkseducate.securityapp.application.usecase.ToolItem;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.domain.model.ToolItem;
import com.bkseducate.securityapp.domain.model.ToolItemStatus;
import com.bkseducate.securityapp.domain.ports.ToolItemRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToolItemUpdateStatusUseCase {
    
    private final ToolItemRepository toolItemRepository;

    public void execute(UUID itemId, ToolItemStatus status, UUID supplierId) {
        ToolItem toolItem = toolItemRepository.findById(itemId)
            .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar el ITEM con ID" + itemId));

        if (!toolItem.getToolCatalog().getSupplier().getUserId().equals(supplierId)) 
            throw new RuntimeException("No tienes permisos para Gestionar este producto");

        ToolItem newtoolItem = ToolItem.recreate(
            toolItem.getId(),
            toolItem.getToolCatalog(),
            status,
            toolItem.getAvaiable()
        );

        toolItemRepository.save(newtoolItem);
    }
}
