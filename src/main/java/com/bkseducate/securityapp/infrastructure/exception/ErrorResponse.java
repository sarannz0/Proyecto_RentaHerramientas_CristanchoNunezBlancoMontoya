package com.bkseducate.securityapp.infrastructure.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

/**
 * Formato estándar de respuesta de error HTTP
 */
@Schema(description = "Estructura estándar para respuestas de error")
public record ErrorResponse(
        @Schema(description = "Marca de tiempo del error", example = "2023-10-25T10:30:00") LocalDateTime timestamp,

        @Schema(description = "Código de estado HTTP", example = "404") int status,

        @Schema(description = "Tipo de error", example = "Not Found") String error,

        @Schema(description = "Mensaje detallado del error", example = "El recurso solicitado no existe") String message,

        @Schema(description = "Ruta de la solicitud que generó el error", example = "/api/resource/1") String path) {
    public static ErrorResponse of(int status, String error, String message, String path) {
        return new ErrorResponse(
                LocalDateTime.now(),
                status,
                error,
                message,
                path);
    }
}
