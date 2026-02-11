package com.bkseducate.securityapp.application.dto.ReporteDano;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.DevolucionItemstatusDano;
import com.bkseducate.securityapp.domain.model.EstadoDevolucion;

public record DevolucionDTO(

    UUID rentId,
    List<UUID> productsId,
    LocalDateTime fechaReporte,
    String descripcion,
    EstadoDevolucion estadoDevolucion,
    BigDecimal costoReparacion,
    DevolucionItemstatusDano devolucionItemstatusDano

    

) {}