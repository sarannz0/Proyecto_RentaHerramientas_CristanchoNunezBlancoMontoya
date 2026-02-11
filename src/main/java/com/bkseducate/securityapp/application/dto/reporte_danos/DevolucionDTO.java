package com.bkseducate.securityapp.application.dto.reporte_danos;

import jakarta.validation.constraints.NotNull;

public record DevolucionDTO(
    @NotNull(message = "el estado es requerido")
    String estado,
    String descripcion,
    double costoReparacion
) {}
