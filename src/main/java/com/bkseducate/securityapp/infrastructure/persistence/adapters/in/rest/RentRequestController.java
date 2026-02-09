package com.bkseducate.securityapp.infrastructure.persistence.adapters.in.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bkseducate.securityapp.application.usecase.RentRequest.GetRentRequestUseCase;
import com.bkseducate.securityapp.application.usecase.RentRequest.UpdateRentRequestUseCase;
import com.bkseducate.securityapp.domain.model.RentRequest;
import com.bkseducate.securityapp.domain.model.RentRequestStatus;
import com.bkseducate.securityapp.infrastructure.exception.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Solicitudes de Renta", description = "Gestión de solicitudes de alquiler para proveedores")
@RestController
@RequestMapping("/rent-request")
@RequiredArgsConstructor
public class RentRequestController {

    private final GetRentRequestUseCase getRentRequestUseCase;
    private final UpdateRentRequestUseCase updateRentRequestUseCase;

    @Operation(summary = "Listar solicitudes", description = "Obtiene las solicitudes de renta asociadas al proveedor autenticado. Requiere rol SUPPLIER.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente", content = @Content(schema = @Schema(implementation = RentRequest.class))),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<List<RentRequest>> getRequests(Authentication authentication) {
        UUID supplierId = (UUID) authentication.getPrincipal();
        return ResponseEntity.ok(getRentRequestUseCase.execute(supplierId));
    }

    @Operation(summary = "Actualizar estado de solicitud", description = "Acepta o rechaza una solicitud de renta. Requiere rol SUPPLIER.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado actualizado exitosamente"),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/status/{id}")
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<Void> updateStatus(
            @Parameter(description = "ID de la solicitud", required = true) @PathVariable UUID id,
            @Parameter(description = "Nuevo estado (ACCEPTED, DENEGED)", required = true) @RequestParam RentRequestStatus status) {
        updateRentRequestUseCase.execute(id, status);
        return ResponseEntity.ok().build();
    }
}