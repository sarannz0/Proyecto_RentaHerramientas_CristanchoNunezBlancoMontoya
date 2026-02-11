package com.bkseducate.securityapp.application.dto.Devolucion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public class DevolucionDTO {

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "OK|DAÑOS", message = "El estado debe ser 'OK' o 'DAÑOS'")
    private String estado;

    private String descripcionDano;

    @PositiveOrZero(message = "El costo de reparación no puede ser negativo")
    private Double costoReparacion;

    public DevolucionDTO() {}

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcionDano() {
        return descripcionDano;
    }

    public void setDescripcionDano(String descripcionDano) {
        this.descripcionDano = descripcionDano;
    }

    public Double getCostoReparacion() {
        return costoReparacion;
    }

    public void setCostoReparacion(Double costoReparacion) {
        this.costoReparacion = costoReparacion;
    }
}
