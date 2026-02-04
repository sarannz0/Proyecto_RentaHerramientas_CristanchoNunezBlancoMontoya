package com.bkseducate.securityapp.infrastructure.persistence.adapters.in.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkseducate.securityapp.application.dto.Profile.SupplierResponse;
import com.bkseducate.securityapp.application.dto.Profile.UserResponse;
import com.bkseducate.securityapp.application.dto.Supplier.SupplierRequest;
import com.bkseducate.securityapp.application.usecase.User.CreateSupplierUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    
    private final CreateSupplierUseCase createSupplierUseCase;

    public SupplierController(
        CreateSupplierUseCase createSupplierUseCase
    ) {
        this.createSupplierUseCase = createSupplierUseCase;
    }

    @Operation(
        summary = "Registrar Proveedor en la App",
        description = "Registra los usuarios de tipo Proveedor (SUPPLIER). Requiere rol ADMIN."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proveedor registrado exitosamente",
            content = @Content(schema = @Schema(implementation = UserResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos (requiere ADMIN)"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PostMapping("/suppliers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SupplierResponse> createSupplier(
        @Valid @RequestBody SupplierRequest request
    ) {
        SupplierResponse supplier = createSupplierUseCase.execute(request);
        return ResponseEntity.ok(supplier);
    }  
}
