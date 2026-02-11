package com.bkseducate.securityapp.infrastructure.persistence.adapters.in.rest;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkseducate.securityapp.application.dto.reporte_danos.DevolucionDTO;
import com.bkseducate.securityapp.application.usecase.ReporteDano.DevolucionUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReporteDanosController {
    
    private final DevolucionUseCase devolucionUseCase;

    @PreAuthorize("hasRole('SUPPLIER')")
    @PostMapping("/{id}/registrar-devolucion")
    public ResponseEntity<HttpStatus> createDevolucion(
        @PathVariable UUID id,
        @RequestBody DevolucionDTO devolucionDTO
    ) { 
        devolucionUseCase.execute(id, devolucionDTO);
        return ResponseEntity.ok().build();

    }

    
}
