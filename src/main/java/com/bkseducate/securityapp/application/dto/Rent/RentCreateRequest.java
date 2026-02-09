package com.bkseducate.securityapp.application.dto.Rent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.PaymentMethod;
import com.bkseducate.securityapp.domain.model.ToolCatalog;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Solicitud para crear un alquiler de herramientas")
public record RentCreateRequest(

    @Schema(description = "IDs de los ítems de herramientas a alquilar", example = "[\"550e8400-e29b-41d4-a716-446655440000\"]", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "La lista de productos es requerida")
    List<UUID> productsId,

    @Schema(description = "Fecha y hora de inicio del alquiler", example = "2024-01-15T09:00:00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El Start Date es requerido")
    LocalDateTime startDate,

    @Schema(description = "Fecha y hora de fin del alquiler", example = "2024-01-20T18:00:00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El EndDate es requerido")
    LocalDateTime endDate,

    @Schema(description = "Dirección de entrega/recogida", example = "calle 10N....")
    @NotBlank(message = "El addresDesc es requerido")
    String addressDesc,

    @Schema(description = "Codigo postaldel Supplier", example = "1234")
    @NotBlank(message = "El PostalCode es requerido")
    String postalCode,

    @Schema(description = "Ciudad del Supplier", example = "Bogota D.C.")
    @NotBlank(message = "El CityName es requerido")
    String cityName,
    
    @Schema(description = "IsoCode del pais del Supplier", example = "COL, MEX, ARG")
    @NotBlank(message = "El countryIsocode es requerido")
    String countryIsocode,

    @Schema(description = "Método de pago", example = "CARD", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El metodo de pago es requerido")
    PaymentMethod paymentMethod
) {}
