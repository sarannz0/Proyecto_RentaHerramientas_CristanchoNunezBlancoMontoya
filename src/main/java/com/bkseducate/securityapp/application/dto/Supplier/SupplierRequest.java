package com.bkseducate.securityapp.application.dto.Supplier;

import com.bkseducate.securityapp.domain.model.Address;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SupplierRequest(
    @Schema(description = "Nombre del usuario", example = "Ponscio", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Nombre es requerido")
    @Size(min = 3, max = 16, message = "El Nombre debe contener entre 3 y 16 caracteres")
    String name,

    @Schema(description = "Email del usuario", example = "user@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Email es requerido")
    @Email(message = "Email debe tener un formato válido")
    String email,
    
    @Schema(description = "Contraseña del usuario (mínimo 6 caracteres)", example = "password123", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Password es requerido")
    @Size(min = 6, message = "Password debe tener al menos 6 caracteres")
    String password,

    @Schema(description = "Nombre de la empresa", example = "DeWalt")
    @NotBlank(message = "El companyName es requerido")
    String companyName,

    @Schema(description = "Dirección del Supplier", example = "calle 10N....")
    @NotBlank(message = "El addresId es requerido")
    String addressDesc,
    String postalCode,
    String cityName,
    String countryIsocode
) {}
