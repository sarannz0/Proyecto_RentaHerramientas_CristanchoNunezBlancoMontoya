package com.bkseducate.securityapp.infrastructure.persistence.adapters.in.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence.CountryRepositoryAdapter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Países", description = "Endpoints para consulta de información de países")
@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    CountryRepositoryAdapter countryRepositoryAdapter;

    @Operation(summary = "Obtener país por ID", description = "Retorna el ID del país si existe. Requiere rol ADMIN.")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "País encontrado", content = @Content(schema = @Schema(implementation = UUID.class))),
            @ApiResponse(responseCode = "403", description = "No autorizado (Requiere ADMIN)"),
            @ApiResponse(responseCode = "404", description = "País no encontrado")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UUID getPais(
            @Parameter(description = "ID del país", required = true) @PathVariable UUID id) {
        return countryRepositoryAdapter.findById(id).get().getId();
    }

}
