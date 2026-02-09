package com.bkseducate.securityapp.application.dto.Rent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.PaymentMethod;
import com.bkseducate.securityapp.domain.model.ToolCatalog;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RentCreateRequest(

    @NotNull
    List<UUID> productsId,

    @NotNull(message = "El Start Date es requerido")
    LocalDateTime startDate,

    @NotNull(message = "El EndDate es requerdio")
    LocalDateTime endDate,

    @Schema(description = "Dirección del Supplier", example = "calle 10N....")
    @NotBlank(message = "El addresDesc es requerido")
    String addressDesc,

    @Schema(description = "Codigo postaldel Supplier", example = "1234")
    @NotBlank(message = "El PostalCode es requerido")
    String postalCode,

    @Schema(description = "Ciudad del Supplier", example = "Bogota D.C.")
    @NotBlank(message = "El CityName es requerido")
    String cityName,
    
    @Schema(description = "IsoCode del pais del Supplier", example = "COL, MEX, ARG")
    @NotBlank(message = "El addresDesc es requerido")
    String countryIsocode,

    @NotNull(message = "El metodo de pago es requerido")
    PaymentMethod paymentMethod
) {}
