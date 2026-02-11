package com.bkseducate.securityapp.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reporte_dano")
public class ReporteDanoEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(nullable = false)
    private Double costoReparacion;

    @Column(nullable = false)
    private LocalDateTime fechaReporte;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rent_id", nullable = false)
    private RentEntity rent;

    public ReporteDanoEntity() {}

    @PrePersist
    public void prePersist() {
        this.fechaReporte = LocalDateTime.now();
    }

    // Getters y Setters

    public UUID getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCostoReparacion() {
        return costoReparacion;
    }

    public void setCostoReparacion(Double costoReparacion) {
        this.costoReparacion = costoReparacion;
    }

    public LocalDateTime getFechaReporte() {
        return fechaReporte;
    }

    public RentEntity getRent() {
        return rent;
    }

    public void setRent(RentEntity rent) {
        this.rent = rent;
    }
}
