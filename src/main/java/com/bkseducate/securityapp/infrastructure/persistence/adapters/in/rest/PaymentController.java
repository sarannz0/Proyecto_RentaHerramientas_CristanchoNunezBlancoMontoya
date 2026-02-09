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

@Tag(name = "Pagos", description = "Gestión de pagos de rentas")
@RestController
@RequestMapping("/pay")
@RequiredArgsConstructor
public class PaymentController {
    
    private final PaymentCreateUseCase paymentCreateUseCase;

    @Operation(summary = "Realizar pago", description = "Registra un pago para una renta específica. Requiere rol USER.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pago creado exitosamente"),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Renta no encontrada", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/{rentId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> createPayment(
        @Parameter(description = "ID de la renta", required = true) @PathVariable UUID rentId,
        Authentication authentication,
        @Parameter(description = "Método de pago (CARD, TRANSFER)", required = true) @RequestParam PaymentMethod method
    ) {
        UUID userId = (UUID) authentication.getPrincipal();
        paymentCreateUseCase.execute(userId, rentId, method);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    

}
