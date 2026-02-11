package com.bkseducate.securityapp.infrastructure.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.PagoEstado;
import com.bkseducate.securityapp.domain.model.PaymentMethod;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "historial_pago")
public class HistorialEntity {
    
    @Id 
    @Column (columnDefinition = "CHAR(36)", length = 36)
    private UUID id;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDateTime fecha_pago;

    @Column(nullable = false, precision = 10, scale = 2)
    private PaymentEntity amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod metodo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PagoEstado estado;

    
    public HistorialEntity(UUID id, LocalDateTime fecha_pago, PaymentEntity amount, PaymentMethod metodo,
            PagoEstado estado) {
        this.id = id;
        this.fecha_pago = fecha_pago;
        this.amount = amount;
        this.metodo = metodo;
        this.estado = estado;
    }

    public HistorialEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getfecha_pago() {
        return fecha_pago;
    }

    public void setfecha_pago(LocalDateTime fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public PaymentEntity getAmount() {
        return amount;
    }

    public void setAmount(PaymentEntity amount) {
        this.amount = amount;
    }

    public PaymentMethod getMetodo() {
        return metodo;
    }

    public void setMetodo(PaymentMethod metodo) {
        this.metodo = metodo;
    }

    public PagoEstado getEstado() {
        return estado;
    }

    public void setEstado(PagoEstado estado) {
        this.estado = estado;
    }


    

}
