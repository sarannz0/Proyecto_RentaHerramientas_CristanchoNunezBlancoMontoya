package com.bkseducate.securityapp.infrastructure.persistence.adapters.in.rest;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkseducate.securityapp.application.usecase.Invoice.GetInvoiceUseCase;
import com.bkseducate.securityapp.domain.model.Invoice;
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

@Tag(name = "Facturas", description = "Gestión de facturas")
@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final GetInvoiceUseCase getInvoiceUseCase;

    @Operation(summary = "Obtener factura", description = "Obtiene la factura asociada a una renta. Requiere rol USER.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura obtenida exitosamente", content = @Content(schema = @Schema(implementation = Invoice.class))),
            @ApiResponse(responseCode = "400", description = "Parámetros de solicitud inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "No autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "No autorizado (Requiere USER)", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{rentId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Invoice> getInvoiceByRentId(
            @Parameter(description = "ID de la renta", required = true) @PathVariable UUID rentId) {
        return ResponseEntity.ok(getInvoiceUseCase.execute(rentId));
    }
}