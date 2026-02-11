package com.bkseducate.securityapp.infrastructure.persistence.adapters.in.rest;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkseducate.securityapp.application.usecase.Historial.GetHistorialUseCase;
import com.bkseducate.securityapp.domain.model.HistorialPago;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Historial", description = "Historial de pagos usuario")
@RestController
@RequestMapping("/historial")
@RequiredArgsConstructor
public class HistorialController {
    private final GetHistorialUseCase getHistorialUseCase;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/pagos/cliente/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HistorialPago> getHistorialPagoByPaymentId(
             @PathVariable UUID paymentId) {
                  return ResponseEntity.ok(getHistorialUseCase.execute(paymentId));
           }
}
