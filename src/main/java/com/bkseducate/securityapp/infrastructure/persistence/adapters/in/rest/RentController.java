package com.bkseducate.securityapp.infrastructure.persistence.adapters.in.rest;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkseducate.securityapp.application.dto.Rent.RentCreateRequest;
import com.bkseducate.securityapp.application.usecase.Rent.CreateRentUseCase;
import com.bkseducate.securityapp.domain.model.Rent;
import com.bkseducate.securityapp.infrastructure.exception.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Alquileres", description = "Gestión de alquileres de herramientas")
@RestController
@RequestMapping("/rent")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class RentController {
    private final CreateRentUseCase createRentUseCase;

    @Operation(summary = "Crear alquiler", description = "Crea un nuevo alquiler de herramientas para el usuario autenticado. Requiere rol USER.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alquiler creado exitosamente", content = @Content(schema = @Schema(implementation = Rent.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o error de negocio", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "No autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "No autorizado (Requiere USER)", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<Rent> createRent(
        @Valid @RequestBody RentCreateRequest request,
        Authentication authentication
    ) {
        UUID userId = (UUID) authentication.getPrincipal();
        return ResponseEntity.ok(createRentUseCase.execute(userId, request));
    }
}
