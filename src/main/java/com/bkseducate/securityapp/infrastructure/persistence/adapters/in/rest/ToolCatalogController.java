package com.bkseducate.securityapp.infrastructure.persistence.adapters.in.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bkseducate.securityapp.application.dto.ToolCatalog.ToolCatalogRequest;
import com.bkseducate.securityapp.application.dto.ToolCatalog.ToolCatalogUpdateRequest;
import com.bkseducate.securityapp.application.usecase.ToolCatalog.CreateToolCatalogUseCase;
import com.bkseducate.securityapp.application.usecase.ToolCatalog.DeleteToolCatalogUseCase;
import com.bkseducate.securityapp.application.usecase.ToolCatalog.ListToolCatalogByIdUseCase;
import com.bkseducate.securityapp.application.usecase.ToolCatalog.ListToolCatalogUseCase;
import com.bkseducate.securityapp.application.usecase.ToolCatalog.UpdateToolCatalogUseCase;
import com.bkseducate.securityapp.domain.model.ToolCatalog;
import com.bkseducate.securityapp.infrastructure.exception.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Catálogo Herramientas", description = "Gestión del catálogo de herramientas")
@RestController
@RequestMapping("/tools")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ToolCatalogController {
        private final CreateToolCatalogUseCase createToolCatalogUseCase;
        private final ObjectMapper objectMapper;
        private final DeleteToolCatalogUseCase deleteToolCatalogUseCase;
        private final UpdateToolCatalogUseCase updateToolCatalogUseCase;
        private final ListToolCatalogByIdUseCase listToolCatalogByIdUseCase;
        private final ListToolCatalogUseCase listToolCatalogUseCase;

        @Operation(summary = "Crear catálogo de herramientas", description = "Crea un nuevo catálogo de herramientas con imagen. Requiere rol SUPPLIER.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Catálogo creado exitosamente"),
                        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "401", description = "No autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "403", description = "No autorizado (Requiere SUPPLIER)", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        })
        @PreAuthorize("hasRole('SUPPLIER')")
        @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<HttpStatus> createToolCatalog(
                        @Parameter(description = "Imagen de la herramienta") @RequestPart("imgFile") MultipartFile imgFile,
                        @Parameter(description = "JSON string of ToolCatalogRequest (serialized)") @RequestPart String request,
                        Authentication authentication) throws JsonProcessingException {
                UUID supplierId = (UUID) authentication.getPrincipal();
                createToolCatalogUseCase.execute(
                                supplierId,
                                objectMapper.readValue(request, ToolCatalogRequest.class),
                                imgFile);
                return ResponseEntity.ok().build();
        }

        @Operation(summary = "Eliminar catálogo de herramientas", description = "Elimina un catálogo existente. Requiere rol SUPPLIER.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Eliminado exitosamente"),
                        @ApiResponse(responseCode = "401", description = "No autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "403", description = "No autorizado (Requiere SUPPLIER)", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Catálogo no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        })
        @PreAuthorize("hasRole('SUPPLIER')")
        @DeleteMapping("/delete/{catalogId}")
        public ResponseEntity<HttpStatus> deleteToolCatalog(
                        @Parameter(description = "ID del catálogo", required = true) @PathVariable UUID catalogId,
                        Authentication authentication) throws JsonProcessingException {
                UUID supplierId = (UUID) authentication.getPrincipal();
                deleteToolCatalogUseCase.execute(
                                catalogId,
                                supplierId);
                return ResponseEntity.status(HttpStatus.OK).build();
        }

        @Operation(summary = "Actualizar catálogo de herramientas", description = "Actualiza la información e imagen de un catálogo. Requiere rol SUPPLIER.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Actualizado exitosamente"),
                        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "401", description = "No autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "403", description = "No autorizado (Requiere SUPPLIER)", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Catálogo no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        })
        @PreAuthorize("hasRole('SUPPLIER')")
        @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, path = "/update/{catalogId}")
        public ResponseEntity<HttpStatus> updateToolCatalog(
                        @Parameter(description = "Imagen de la herramienta") @RequestPart("imgFile") MultipartFile imgFile,
                        @Parameter(description = "ID del catálogo", required = true) @PathVariable UUID catalogId,
                        @Parameter(description = "JSON string of ToolCatalogUpdateRequest") @RequestPart String request,
                        Authentication authentication) throws JsonProcessingException {
                UUID supplierId = (UUID) authentication.getPrincipal();
                updateToolCatalogUseCase.execute(
                                catalogId,
                                supplierId,
                                objectMapper.readValue(request, ToolCatalogUpdateRequest.class),
                                imgFile);
                return ResponseEntity.ok().build();
        }

        @Operation(summary = "Listar catálogos del proveedor", description = "Obtiene todos los catálogos de herramientas del proveedor autenticado. Requiere rol SUPPLIER.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de catálogos del proveedor", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ToolCatalog.class)))),
                        @ApiResponse(responseCode = "401", description = "No autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "403", description = "No autorizado (Requiere SUPPLIER)", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        })
        @PreAuthorize("hasRole('SUPPLIER')")
        @GetMapping("/list")
        public ResponseEntity<List<ToolCatalog>> findAllBySupplierId(
                Authentication authentication
        ) {
                UUID supplierId = (UUID) authentication.getPrincipal();
                return ResponseEntity.ok(listToolCatalogByIdUseCase.execute(supplierId)); 
        }       
        
        @Operation(summary = "Listar todos los catálogos", description = "Obtiene todos los catálogos de herramientas disponibles. Requiere rol USER.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de todos los catálogos", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ToolCatalog.class)))),
                        @ApiResponse(responseCode = "401", description = "No autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "403", description = "No autorizado (Requiere USER)", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        })
        @PreAuthorize("hasRole('USER')")
        @GetMapping("/list/all")
        public ResponseEntity<List<ToolCatalog>> findAll() {
                return ResponseEntity.ok(listToolCatalogUseCase.execute()); 
        }
}
