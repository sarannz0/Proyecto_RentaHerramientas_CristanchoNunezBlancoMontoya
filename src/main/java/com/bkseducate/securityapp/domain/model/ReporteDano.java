package com.bkseducate.securityapp.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.bkseducate.securityapp.domain.exceptions.DomainException;

import java.util.HashSet;
import java.util.Set;

public class ReporteDano {

    private UUID id;
    private LocalDateTime fechaReporte;
    private Set<Rent> rent = new HashSet<>();
    private Set<ToolItem> toolItem = new HashSet<>();
    private String descripcion;
    private EstadoDevolucion esDevolucion;
    private User user;
    private BigDecimal costoReparacion;
    
    public static ReporteDano create(
        LocalDateTime fechaReporte,
        User user
    ) {
        ReporteDano reporteDano = new ReporteDano();
        reporteDano.id = UUID.randomUUID();
        reporteDano.fechaReporte = fechaReporte;
        reporteDano.user = user;
        reporteDano.esDevolucion = EstadoDevolucion.PENDIENTE_DEVOLUCION;

        return reporteDano;
    }

    public static ReporteDano recreate(
        UUID id,
        LocalDateTime fechaReporte,
        Set<Rent> rent,
        Set<ToolItem> toolItem,
        String descripcion,
        EstadoDevolucion esDevolucion,
        User user,
        BigDecimal costoReparacion
    ) {
        ReporteDano reporteDano = new ReporteDano();
        reporteDano.id = id;
        reporteDano.fechaReporte = fechaReporte;
        reporteDano.toolItem = toolItem;
        reporteDano.esDevolucion = esDevolucion;
    
        return reporteDano;
    }

    public void updateStatus(EstadoDevolucion esDevolucion) {
        if (esDevolucion == null) throw new DomainException("El status no puede ser nulo");
        this.esDevolucion = esDevolucion;
    }

    public void assignTool(ToolItem toolItem) {
        if (toolItem == null) throw new DomainException("El item no puede ser nulo");
        this.toolItem.add(toolItem);
    }

    public void removeToo(ToolItem toolItem) {
        if (toolItem == null) throw new DomainException("El item no puede ser nulo");
        this.toolItem.remove(toolItem);
    }

    
    public UUID getId() {
        return id;
    }

    public LocalDateTime getFechaReporte() {
        return fechaReporte;
    }

    public Set<Rent> getRent() {
        return rent;
    }

    public Set<ToolItem> getToolItem() {
        return toolItem;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public EstadoDevolucion getEsDevolucion() {
        return esDevolucion;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getCostoReparacion() {
        return costoReparacion;
    }

    private ReporteDano() {
    }

}

