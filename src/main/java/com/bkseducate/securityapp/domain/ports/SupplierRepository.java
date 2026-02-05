package com.bkseducate.securityapp.domain.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bkseducate.securityapp.application.dto.Supplier.SupplierRequest;
import com.bkseducate.securityapp.domain.model.SupplierM;

public interface SupplierRepository {
    List<SupplierM> findAll();
    Optional<SupplierM> findById(UUID userId);
    SupplierM save(SupplierM supplier);
    SupplierM update(UUID userId, SupplierRequest supplierM);
    void delete(UUID userId);
}
