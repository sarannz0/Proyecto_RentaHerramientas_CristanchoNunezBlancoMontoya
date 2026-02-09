package com.bkseducate.securityapp.domain.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.Invoice;

public interface InvoiceRepository {
    
    List<Invoice> findAll();
    Optional<Invoice> findById(UUID id);
    Invoice save(Invoice invoice);
    void delete(UUID id);
}
