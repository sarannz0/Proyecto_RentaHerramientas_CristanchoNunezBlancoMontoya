package com.bkseducate.securityapp.application.dto.Address;

import com.bkseducate.securityapp.domain.model.City;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para crear/actualizar dirección")
public record AddressRequest(
        @Schema(description = "Dirección física", example = "Carrera 7 # 32-15", requiredMode = Schema.RequiredMode.REQUIRED) String address,

        @Schema(description = "Código postal", example = "110221", requiredMode = Schema.RequiredMode.REQUIRED) String postalCode,

        @Schema(description = "Ciudad asociada", requiredMode = Schema.RequiredMode.REQUIRED) City city) {
}
