package com.bkseducate.securityapp.application.usecase.RentRequest;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.domain.exceptions.UserNotFoundException;
import com.bkseducate.securityapp.domain.model.RentRequest;
import com.bkseducate.securityapp.domain.model.SupplierM;
import com.bkseducate.securityapp.domain.ports.RentRequestRepository;
import com.bkseducate.securityapp.domain.ports.SupplierRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetRentRequestUseCase {
    
    private final RentRequestRepository rentRequestRepository;
    private final SupplierRepository supplierRepository;

    public List<RentRequest> execute(UUID supplierId) {
        SupplierM supplierM = supplierRepository.findById(supplierId)
            .orElseThrow(() -> new UserNotFoundException("No se pudo encontrar un proveedor con ID " + supplierId));
            
        return rentRequestRepository.findAll().stream()
            .filter(request -> request.getRent().getToolItem().stream()
                .anyMatch(item -> item.getToolCatalog().getSupplier().getUserId().equals(supplierId)))
            .toList();
    }
}
