package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.domain.model.Invoice;
import com.bkseducate.securityapp.domain.ports.InvoiceRepository;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.InvoiceMapper;
import com.bkseducate.securityapp.infrastructure.persistence.repository.InvoiceJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceRepositoryAdapter implements InvoiceRepository {
    
    private final InvoiceJpaRepository jpaRepository;
    private final InvoiceMapper invoiceMapper;
    
    @Override
    public List<Invoice> findAll() {
        return jpaRepository.findAll().stream().map(invoiceMapper::toDomain).toList();
    }
    @Override
    public Optional<Invoice> findById(UUID id) {
        return jpaRepository.findById(id).map(invoiceMapper::toDomain);
    }
    @Override
    public Invoice save(Invoice invoice) {
        return invoiceMapper.toDomain(jpaRepository.save(invoiceMapper.toEntity(invoice)));
    }
    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    }    
}
