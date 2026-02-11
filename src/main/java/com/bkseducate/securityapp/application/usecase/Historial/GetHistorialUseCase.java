package com.bkseducate.securityapp.application.usecase.Historial;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.domain.model.HistorialPago;
import com.bkseducate.securityapp.domain.ports.HistorialRepository;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class GetHistorialUseCase {
        private final HistorialRepository historialRepository;

    public HistorialPago execute(UUID paymentId) {
        return historialRepository.findById(paymentId)
                .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar el Payment con ID: " + paymentId));
    }
}
