package com.bkseducate.securityapp.application.usecase.ToolCatalog;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.domain.exceptions.UserNotFoundException;
import com.bkseducate.securityapp.domain.model.SupplierM;
import com.bkseducate.securityapp.domain.model.ToolCatalog;
import com.bkseducate.securityapp.domain.ports.SupplierRepository;
import com.bkseducate.securityapp.domain.ports.ToolCatalogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListToolCatalogByIdUseCase {
    
    private final ToolCatalogRepository toolCatalogRepository;
    private final SupplierRepository supplierRepository;

    public List<ToolCatalog> execute(UUID supplierId) {
        SupplierM supplierM = supplierRepository.findById(supplierId)
            .orElseThrow(() -> new UserNotFoundException("No se pudo encontrar un proveedor con ID " + supplierId));
        return toolCatalogRepository.findAll().stream().filter(tool -> tool.getSupplier().getUserId().equals(supplierM.getUserId())).toList();
    }   
}
