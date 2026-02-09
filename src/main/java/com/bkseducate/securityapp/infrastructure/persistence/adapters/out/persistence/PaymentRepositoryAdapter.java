package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.domain.model.Payment;
import com.bkseducate.securityapp.domain.ports.PaymentRepository;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.PaymentMapper;
import com.bkseducate.securityapp.infrastructure.persistence.repository.PaymentJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepository{
    
    private PaymentJpaRepository jpaRepository;
    private PaymentMapper paymentMapper;

    @Override
    public List<Payment> findAll() {
        return jpaRepository.findAll().stream().map(paymentMapper::toDomain).toList();
    }

    @Override
    public Optional<Payment> findById(UUID id) {
        return jpaRepository.findById(id).map(paymentMapper::toDomain);
    }

    @Override
    public Payment save(Payment payment) {
        return paymentMapper.toDomain(jpaRepository.save(paymentMapper.toEntity(payment)));
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    }
}
