package com.bkseducate.securityapp.application.dto.Profile;

import java.util.Set;
import java.util.UUID;

import com.bkseducate.securityapp.application.dto.Role.RoleResponse;
import com.bkseducate.securityapp.domain.model.Address;
import com.bkseducate.securityapp.domain.model.UserStatus;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información del Proveedor")
public record SupplierResponse(
    @Schema(description = "ID del usuario", example = "550e8400-e29b-41d4-a716-446655440000")
    UUID id,
    @Schema(description = "Nombre del proveedor", example = "Ponscio")
    String name,
    @Schema(description = "Email del proveedor", example = "proveedor@example.com")
    String email,
    @Schema(description = "Roles asignados al usuario")
    Set<RoleResponse> roles,
    @Schema(description = "Estado del usuario", example = "ACTIVE")
    UserStatus status,
    @Schema(description = "Nombre de la empresa", example = "DeWalt")
    String companyName,
    @Schema(description = "Dirección del proveedor")
    Address address
) implements ProfileResponse {}
