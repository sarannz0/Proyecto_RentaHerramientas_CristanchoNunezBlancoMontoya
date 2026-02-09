package com.bkseducate.securityapp.infrastructure.persistence.adapters.in.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkseducate.securityapp.application.dto.ToolItem.ToolUpdateStatusRequest;
import com.bkseducate.securityapp.application.usecase.ToolItem.ToolITemDeleteUseCase;
import com.bkseducate.securityapp.application.usecase.ToolItem.ToolITemGetAllUseCase;
import com.bkseducate.securityapp.application.usecase.ToolItem.ToolItemCreateUseCase;
import com.bkseducate.securityapp.application.usecase.ToolItem.ToolItemUpdateAvaiableUseCase;
import com.bkseducate.securityapp.application.usecase.ToolItem.ToolItemUpdateStatusUseCase;
import com.bkseducate.securityapp.domain.model.ToolItem;
import com.bkseducate.securityapp.infrastructure.exception.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Items Herramientas", description = "Gestión de los items físicos de herramientas")
@RestController
@RequestMapping("/tool/item")
@RequiredArgsConstructor
public class ToolItemController {

        private final ToolItemCreateUseCase toolItemCreateUseCase;
        private final ToolITemDeleteUseCase toolITemDeleteUseCase;
        private final ToolITemGetAllUseCase toolITemGetAllUseCase;
        private final ToolItemUpdateStatusUseCase toolItemUpdateStatusUseCase;
        private final ToolItemUpdateAvaiableUseCase toolItemUpdateAvaiableUseCase;

        @Operation(summary = "Crear ítem de herramienta", description = "Crea un ítem físico asociado a un catálogo. Requiere rol SUPPLIER.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Item creado exitosamente"),
                        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "401", description = "No autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "403", description = "No autorizado (Requiere SUPPLIER)", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Catálogo no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        })
        @SecurityRequirement(name = "bearerAuth")
        @PostMapping("/create/{catalogId}")
        @PreAuthorize("hasRole('SUPPLIER')")
        public ResponseEntity<HttpStatus> create(
                        @Parameter(description = "ID del catálogo", required = true) @PathVariable UUID catalogId,
                        Authentication authentication) {
                UUID supplierId = (UUID) authentication.getPrincipal();
                toolItemCreateUseCase.execute(catalogId, supplierId);
                return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        @Operation(summary = "Eliminar ítem de herramienta", description = "Elimina un ítem específico. Requiere rol SUPPLIER.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Eliminado exitosamente"),
                        @ApiResponse(responseCode = "401", description = "No autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "403", description = "No autorizado (Requiere SUPPLIER)", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Ítem no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        })
        @SecurityRequirement(name = "bearerAuth")
        @DeleteMapping("/delete/{itemId}")
        @PreAuthorize("hasRole('SUPPLIER')")
        public ResponseEntity<HttpStatus> delete(
                        @Parameter(description = "ID del ítem", required = true) @PathVariable UUID itemId,
                        Authentication authentication) {
                UUID supplierId = (UUID) authentication.getPrincipal();
                toolITemDeleteUseCase.execute(itemId, supplierId);
                return ResponseEntity.status(HttpStatus.OK).build();
        }

        @Operation(summary = "Listar ítems de un catálogo", description = "Obtiene todos los ítems asociados a un catálogo. Requiere rol SUPPLIER.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de ítems obtenida", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ToolItem.class)))),
                        @ApiResponse(responseCode = "401", description = "No autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "403", description = "No autorizado (Requiere SUPPLIER)", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Catálogo no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        })
        @SecurityRequirement(name = "bearerAuth")
        @GetMapping("/get/{catalogId}")
        @PreAuthorize("hasRole('SUPPLIER')")
        public ResponseEntity<List<ToolItem>> getAll(
                        @Parameter(description = "ID del catálogo", required = true) @PathVariable UUID catalogId,
                        Authentication authentication) {
                UUID supplierId = (UUID) authentication.getPrincipal();
                return ResponseEntity.status(HttpStatus.OK).body(toolITemGetAllUseCase.execute(catalogId, supplierId));
        }

        @Operation(summary = "Actualizar estado físico de un ítem", description = "Cambia el estado (e.g. DAMAGED, MAINTENANCE) de un ítem. Requiere rol SUPPLIER.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Estado actualizado"),
                        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "401", description = "No autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "403", description = "No autorizado (Requiere SUPPLIER)", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Ítem no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        })
        @SecurityRequirement(name = "bearerAuth")
        @PutMapping("/update/status/{itemId}")
        @PreAuthorize("hasRole('SUPPLIER')")
        public ResponseEntity<HttpStatus> updateStatus(
                        @Parameter(description = "ID del ítem", required = true) @PathVariable UUID itemId,
                        Authentication authentication,
                        @Valid @RequestBody ToolUpdateStatusRequest status) {
                UUID supplierId = (UUID) authentication.getPrincipal();
                toolItemUpdateStatusUseCase.execute(itemId, status.status(), supplierId);
                return ResponseEntity.status(HttpStatus.OK).build();
        }

        @Operation(summary = "Actualizar disponibilidad de un ítem", description = "Habilita o deshabilita la disponibilidad de un ítem. Requiere rol SUPPLIER.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Disponibilidad actualizada"),
                        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "401", description = "No autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "403", description = "No autorizado (Requiere SUPPLIER)", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Ítem no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        })
        @SecurityRequirement(name = "bearerAuth")
        @PutMapping("/update/avaible/{itemId}/{bool}")
        @PreAuthorize("hasRole('SUPPLIER')")
        public ResponseEntity<HttpStatus> updateAvaiable(
                        @Parameter(description = "ID del ítem", required = true) @PathVariable UUID itemId,
                        @Parameter(description = "Disponible (true/false)", required = true) @PathVariable Boolean bool,
                        Authentication authentication) {
                UUID supplierId = (UUID) authentication.getPrincipal();
                toolItemUpdateAvaiableUseCase.execute(itemId, bool, supplierId);
                return ResponseEntity.status(HttpStatus.OK).build();
        }
}
