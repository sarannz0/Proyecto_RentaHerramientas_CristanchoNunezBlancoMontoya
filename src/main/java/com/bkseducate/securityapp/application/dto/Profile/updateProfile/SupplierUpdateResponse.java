package com.bkseducate.securityapp.application.dto.Profile.updateProfile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SupplierUpdateResponse(
    @Schema(description = "Nombre del usuario", example = "Ponscio", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Nombre es requerido")
    @Size(min = 3, max = 16, message = "El Nombre debe contener entre 3 y 16 caracteres")
    String name,

    @Schema(description = "Email del usuario", example = "user@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Email es requerido")
    @Email(message = "Email debe tener un formato válido")
    String email,

    @Schema(description = "Nombre de la empresa", example = "DeWalt")
    @NotBlank(message = "El companyName es requerido")
    String companyName,

    @Schema(description = "Direcion del Supplier", example = "calle 10N....")
    @NotBlank(message = "El addresId es requerido")
    String addressId
) implements ProfileUpdate {
    
}
