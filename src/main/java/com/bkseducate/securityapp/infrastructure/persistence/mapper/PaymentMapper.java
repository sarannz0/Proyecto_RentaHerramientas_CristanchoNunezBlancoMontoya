package com.bkseducate.securityapp.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.domain.model.Payment;
import com.bkseducate.securityapp.infrastructure.persistence.entity.PaymentEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentMapper {

    private final RentMapper rebMapper;
    
    public Payment toDomain(PaymentEntity entity) {
        return Payment.recreate(
            entity.getId(),
            rebMapper.toDomain(entity.getRent()),
            entity.getAmount(),
            entity.getDate(),
            entity.getMethod()
        );
    }

    public PaymentEntity toEntity(Payment payment) {
        return new PaymentEntity(
            payment.getId(),
            rebMapper.toEntity(payment.getRentId()),
            payment.getAmount(),
            payment.getDate(),
            payment.getMethod()
        );
    }
}
