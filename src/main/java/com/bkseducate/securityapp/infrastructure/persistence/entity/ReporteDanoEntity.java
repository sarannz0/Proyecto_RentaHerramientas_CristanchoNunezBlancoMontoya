package com.bkseducate.securityapp.infrastructure.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "damage_report")
public class ReporteDanoEntity {


    @Id
    @Column(columnDefinition = "CHAR(36)", length = 36)
    private UUID id;

    @Column(name = "fecha_reporte", nullable = false)
    private LocalDateTime fechaReporte;

    @OneToOne(fetch = FetchType.LAZY, optional = false ,cascade = CascadeType.ALL)
    @JoinColumn(name = "rent_id", nullable = false, updatable = false)
    private Set<RentEntity> rentEntity = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tool_item_id", nullable = true, updatable = false)
    private Set<ToolItemEntity> toolItemEntity = new HashSet<>();
     
    @Column(name = "descripcion")
    private String descripcion;

    @Column (name = "costo_reparacion", nullable = false)
    private BigDecimal costoReparacion;

    public ReporteDanoEntity(UUID id, LocalDateTime fechaReporte, Set<RentEntity> rentEntity,
            Set<ToolItemEntity> toolItemEntity, String descripcion, BigDecimal costoReparacion) {
        this.id = id;
        this.fechaReporte = fechaReporte;
        this.rentEntity = rentEntity;
        this.toolItemEntity = toolItemEntity;
        this.descripcion = descripcion;
        this.costoReparacion = costoReparacion;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(LocalDateTime fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public Set<RentEntity> getRentEntity() {
        return rentEntity;
    }

    public void setRentEntity(Set<RentEntity> rentEntity) {
        this.rentEntity = rentEntity;
    }

    public Set<ToolItemEntity> getToolItemEntity() {
        return toolItemEntity;
    }

    public void setToolItemEntity(Set<ToolItemEntity> toolItemEntity) {
        this.toolItemEntity = toolItemEntity;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getCostoReparacion() {
        return costoReparacion;
    }

    public void setCostoReparacion(BigDecimal costoReparacion) {
        this.costoReparacion = costoReparacion;
    }



}
