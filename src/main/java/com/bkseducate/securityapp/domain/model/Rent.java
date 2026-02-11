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
    private EstadoDevolucion estadoDevolucion;
    private User user;
    private Address address;
    

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
        rent.estadoDevolucion = EstadoDevolucion.PENDIENTE_DEVOLUCION;
        rent.address = address;

        return rent;
    }

    public static Rent recreate(
        UUID id,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal totalAmount,
        Set<ToolItem> toolItem,
        RentStatus status,
        EstadoDevolucion estadoDevolucion,
        User user,
        Address address
    ) {
        Rent rent = new Rent();
        rent.id = id;
        rent.startDate = startDate;
        rent.endDate = endDate;
        rent.totalAmount = totalAmount;
        rent.toolItem = toolItem;
        rent.status = status;
        rent.estadoDevolucion = estadoDevolucion;
        rent.address = address;
        return rent;
    }

    public void updateStatus(RentStatus status) {
        if (status == null) throw new DomainException("El status no puede ser nulo");
        this.status = status;
    }

    public void updateEstadoDevolucion(EstadoDevolucion estadoDevolucion){
        if (estadoDevolucion == null) throw new DomainException("La devolucion no puede ser nula");
        this.estadoDevolucion = estadoDevolucion;
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

    public EstadoDevolucion getEstadoDevolucion() {
        return estadoDevolucion;
    }

    public User getUser() {
        return user;
    }

    public Address getAddress() {
        return address;
    }

    private Rent() {
    }

}

