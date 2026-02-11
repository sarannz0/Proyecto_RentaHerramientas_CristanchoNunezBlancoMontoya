package com.bkseducate.securityapp.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.domain.model.HistorialPago;
import com.bkseducate.securityapp.infrastructure.persistence.entity.HistorialEntity;


import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HistorialMapper {
    private final PaymentMapper payMapper;

    public HistorialPago toDomain(HistorialEntity entity) {
        return HistorialPago.recreate(
            entity.getId(),
            entity.getfecha_pago(),
            payMapper.toDomain(entity.getAmount()),
            entity.getMetodo(),
            entity.getEstado()

        );
    }

    public HistorialEntity toEntity(HistorialPago historial) {
        return new HistorialEntity(
            historial.getId(),
            historial.getFecha_pago(),
            payMapper.toEntity(historial.getAmount()),
            historial.getMetodo(),
            historial.getEstado()

        );

    }
}
