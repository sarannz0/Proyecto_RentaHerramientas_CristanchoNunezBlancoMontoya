package com.bkseducate.securityapp.application.dto.ToolItem;

import com.bkseducate.securityapp.domain.model.ToolItemStatus;

import jakarta.validation.constraints.NotNull;

public record ToolUpdateStatusRequest(
    @NotNull(message = "El status es requerido")
    ToolItemStatus status
) {}
