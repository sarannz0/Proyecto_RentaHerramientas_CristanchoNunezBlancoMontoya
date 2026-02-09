package com.bkseducate.securityapp.infrastructure.persistence.adapters.in.rest;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bkseducate.securityapp.application.usecase.Payment.PaymentCreateUseCase;
import com.bkseducate.securityapp.domain.model.PaymentMethod;
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

@Tag(name = "Pagos", description = "Gestión de pagos de alquileres")
@RestController
@RequestMapping("/pay")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PaymentController {
    
    private final PaymentCreateUseCase paymentCreateUseCase;

    @Operation(summary = "Registrar pago de alquiler", description = "Registra el pago asociado a un alquiler. Requiere rol USER.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pago registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "No autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "No autorizado (Requiere USER)", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Alquiler no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/{rentId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> createPayment(
        @Parameter(description = "ID del alquiler", required = true, example = "550e8400-e29b-41d4-a716-446655440000") @PathVariable UUID rentId,
        @Parameter(description = "Método de pago", required = true, example = "CARD") @RequestParam PaymentMethod method,
        Authentication authentication
    ) {
        UUID userId = (UUID) authentication.getPrincipal();
        paymentCreateUseCase.execute(userId, rentId, method);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    

}
