package com.bkseducate.securityapp.application.usecase.Invoice;

import com.bkseducate.securityapp.domain.model.Invoice;
import com.bkseducate.securityapp.domain.ports.InvoiceRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetInvoiceUseCase {

    private final InvoiceRepository invoiceRepository;

    public Invoice execute(UUID rentId) {
        return invoiceRepository.findById(rentId)
                .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar el INVOICE con ID: " + rentId));
    }
}