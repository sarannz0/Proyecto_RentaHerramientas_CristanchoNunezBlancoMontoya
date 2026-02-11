package com.bkseducate.securityapp.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.bkseducate.securityapp.domain.exceptions.DomainException;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Schema(description = "Alquiler de herramientas asociado a un usuario")
public class Rent {

    private UUID id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal totalAmount;
    private Set<ToolItem> toolItem = new HashSet<>();
    private RentStatus status;
    private User user;
    private Address address;
    private ReporteDanoEnum estadoDevolucion;
    

    

    public static Rent create(
        LocalDateTime startDate,
        LocalDateTime endDate,
        User user,
        Address address
    ) {
        Rent rent = new Rent();
        rent.id = UUID.randomUUID();
        rent.startDate = startDate;
        rent.endDate = endDate;
        rent.user = user;
        rent.status = RentStatus.PENDING;
        rent.address = address;
        rent.estadoDevolucion = ReporteDanoEnum.PENDIENTE_DEVOLUCION;

        return rent;
    }

    public static Rent recreate(
        UUID id,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal totalAmount,
        Set<ToolItem> toolItem,
        RentStatus status,
        User user,
        Address address,
        ReporteDanoEnum estadoDevolucion
    ) {
        Rent rent = new Rent();
        rent.id = id;
        rent.startDate = startDate;
        rent.endDate = endDate;
        rent.totalAmount = totalAmount;
        rent.toolItem = toolItem;
        rent.status = status;
        rent.user = user;
        rent.address = address;
        rent.estadoDevolucion = estadoDevolucion;
        return rent;
    }

    public void updateStatus(RentStatus status) {
        if (status == null) throw new DomainException("El status no puede ser nulo");
        this.status = status;
    }

    public void updateEstadoDevolucion(ReporteDanoEnum estado) {
        if (estado == null ) throw new DomainException("El status no puede ser nulo");
        this.estadoDevolucion = estado;
    }

    public void updateAmount(BigDecimal amount) {
        this.totalAmount = amount;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public Set<ToolItem> getToolItem() {
        return toolItem;
    }

    public RentStatus getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public Address getAddress() {
        return address;
    }

    public ReporteDanoEnum getEstadoDevolucion() {
        return estadoDevolucion;
    }

    private Rent() {
    }

}

