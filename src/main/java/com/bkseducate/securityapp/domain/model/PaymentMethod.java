package com.bkseducate.securityapp.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Método de pago para el alquiler", example = "CARD")
public enum PaymentMethod {
    @Schema(description = "Pago con tarjeta")
    CARD,
    @Schema(description = "Transferencia bancaria")
    TRANSFER
}
