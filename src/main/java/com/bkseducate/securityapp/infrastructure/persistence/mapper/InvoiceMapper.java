package com.bkseducate.securityapp.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.domain.model.Invoice;
import com.bkseducate.securityapp.infrastructure.persistence.entity.InvoiceEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InvoiceMapper {
    
    private final RentMapper rentMapper;

    public Invoice toDomain(InvoiceEntity entity) {
        return Invoice.recreate(entity.getId(),
            rentMapper.toDomain(entity.getRent()),
            entity.getTotalAmount(),
            entity.getDate()
        );
    }

    public InvoiceEntity toEntity(Invoice invoice) {
        return new InvoiceEntity(
            invoice.getId(),
            rentMapper.toEntity(invoice.getRentId()),
            invoice.getDate(),
            invoice.getTotalAmount()
        );
    }
}
