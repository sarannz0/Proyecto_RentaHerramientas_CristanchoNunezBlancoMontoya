package com.bkseducate.securityapp.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReporteDanoRequest {

    private UUID id;
    private ReporteDano reporteDano;
    private LocalDateTime fechaReporte;
    private EstadoDevolucion estadoDevolucion;

    private ReporteDanoRequest() {
    }

    public static ReporteDanoRequest create(ReporteDano reporteDano) {
        ReporteDanoRequest request = new ReporteDanoRequest();
        request.id = UUID.randomUUID();
        request.reporteDano = reporteDano;
        request.fechaReporte = LocalDateTime.now();
        request.estadoDevolucion = EstadoDevolucion.PENDIENTE_DEVOLUCION;
        return request;
    }

    public static ReporteDanoRequest recreate(
        UUID id,
        ReporteDano reporteDano,
        LocalDateTime fechaReporte,
        EstadoDevolucion estadoDevolucion
    ) {
        ReporteDanoRequest request = new ReporteDanoRequest();
        request.id = id;
        request.reporteDano = reporteDano;
        request.fechaReporte = fechaReporte;
        request.estadoDevolucion = estadoDevolucion;
        return request;
    }

    public UUID getId() {
        return id;
    }

    public ReporteDano getReporteDano() {
        return reporteDano;
    }

    public LocalDateTime getFechaReporte() {
        return fechaReporte;
    }

    public EstadoDevolucion getEstadoDevolucion() {
        return estadoDevolucion;
    }

  
}
