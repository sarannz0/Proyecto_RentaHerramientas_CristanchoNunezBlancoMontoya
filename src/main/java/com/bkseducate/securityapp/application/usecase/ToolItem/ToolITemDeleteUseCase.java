package com.bkseducate.securityapp.application.usecase.ToolItem;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.domain.exceptions.DomainException;
import com.bkseducate.securityapp.domain.model.ToolCatalog;
import com.bkseducate.securityapp.domain.model.ToolItem;
import com.bkseducate.securityapp.domain.ports.ToolCatalogRepository;
import com.bkseducate.securityapp.domain.ports.ToolItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToolITemDeleteUseCase {

    private final ToolItemRepository toolItemRepository;

    public void execute(UUID itemId,UUID supplierId) {
        ToolItem toolItem =  toolItemRepository.findById(itemId)
            .orElseThrow(() -> new DomainException("No se pudo encontrar el Catalogo con ID "+ itemId));


        ToolCatalog catalog = toolItem.getToolCatalog();
        
        if (!catalog.getSupplier().getUserId().equals(supplierId)) 
            throw new DomainException("No tienes permisos para gestionar este Catalogo");

        
        toolItemRepository.delete(toolItem.getId());
    }


}
