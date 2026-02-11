package com.bkseducate.securityapp.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class HistorialPago {
    private UUID id;
    private LocalDateTime fecha_pago;
    private Payment amount;
    private PaymentMethod metodo;
    private PagoEstado estado;

    public HistorialPago() {
    }

    public static HistorialPago create(
        LocalDateTime fecha_pago,
        Payment amount,
        PaymentMethod metodo,
        PagoEstado estado
    ) {
        HistorialPago historial = new HistorialPago();
            historial.id = UUID.randomUUID();
            historial.fecha_pago = LocalDateTime.now();
            historial.amount = amount;
            historial.metodo = metodo;
            historial.estado = estado;
            return historial;
    }

    public static HistorialPago recreate(
        UUID id,
        LocalDateTime fecha_pago,
        Payment amount,
        PaymentMethod metodo,
        PagoEstado estado
    ) {
        HistorialPago historial = new HistorialPago();
        historial.id = id;
        historial.fecha_pago = fecha_pago;
        historial.amount = amount;
        historial.metodo = metodo;
        historial.estado = estado;
        return historial;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getFecha_pago() {
        return fecha_pago;
    }

    public Payment getAmount() {
        return amount;
    }

    public PaymentMethod getMetodo() {
        return metodo;
    }

    public PagoEstado getEstado() {
        return estado;
    }

}
