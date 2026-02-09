package com.bkseducate.securityapp.infrastructure.persistence.adapters.in.rest;

import com.bkseducate.securityapp.application.dto.Profile.UserResponse;
import com.bkseducate.securityapp.application.dto.Profile.updateProfile.ProfileUpdate;
import com.bkseducate.securityapp.application.dto.Profile.updateProfile.UpdateRequest;
import com.bkseducate.securityapp.application.dto.Profile.updateProfile.UserUpdateResponse;
import com.bkseducate.securityapp.application.dto.Role.AssignRoleRequest;
import com.bkseducate.securityapp.application.dto.User.StatusUserRequest;
import com.bkseducate.securityapp.application.dto.User.StatusUserResponse;
import com.bkseducate.securityapp.application.usecase.Role.AssignRoleUseCase;
import com.bkseducate.securityapp.application.usecase.User.GetUsersForAdminUseCase;
import com.bkseducate.securityapp.application.usecase.User.InfoUpdateUsersUseCase;
import com.bkseducate.securityapp.application.usecase.User.SetStatusUserUseCase;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controlador REST para gestión de usuarios
 * Requiere autenticación y roles específicos
 */
@Tag(name = "Gestión Usuarios", description = "Endpoints para administración de usuarios y roles")
@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final AssignRoleUseCase assignRoleUseCase;
    private final GetUsersForAdminUseCase getUsersForAdminUseCase;
    private final InfoUpdateUsersUseCase infoUpdateUsersUseCase;
    private final SetStatusUserUseCase setStatusUserUseCase;

    public UserController(
            SetStatusUserUseCase setStatusUserUseCase,
            InfoUpdateUsersUseCase infoUpdateUsersUseCase,
            GetUsersForAdminUseCase getUsersForAdminUseCase,
            AssignRoleUseCase assignRoleUseCase) {
        this.infoUpdateUsersUseCase = infoUpdateUsersUseCase;
        this.getUsersForAdminUseCase = getUsersForAdminUseCase;
        this.assignRoleUseCase = assignRoleUseCase;
        this.setStatusUserUseCase = setStatusUserUseCase;
    }

    @Operation(summary = "Asignar rol a usuario", description = "Asigna un rol específico a un usuario. Requiere rol ADMIN.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol asignado exitosamente", content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "No autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "No autorizado (Requiere ADMIN)", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{userId}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> assignRole(
            @Parameter(description = "ID del usuario", required = true, example = "550e8400-e29b-41d4-a716-446655440000") @PathVariable UUID userId,
            @Valid @RequestBody AssignRoleRequest request) {
        UserResponse response = assignRoleUseCase.execute(userId, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar usuarios", description = "Devuelve una lista de los usuarios registrados (USER/SUPPLIER). Requiere rol ADMIN.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)))),
            @ApiResponse(responseCode = "401", description = "No autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "No autorizado (Requiere ADMIN)", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> listUsers() {
        return ResponseEntity.ok(getUsersForAdminUseCase.execute());
    }

    @Operation(summary = "Actualizar usuario por ADMIN", description = "ADMIN actualiza información no sensible de un usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualizado correctamente", content = @Content(schema = @Schema(implementation = UserUpdateResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "No autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "No autorizado (Requiere ADMIN)", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/update/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProfileUpdate> updateUserByAdmin(
            @Parameter(description = "ID del usuario", required = true, example = "550e8400-e29b-41d4-a716-446655440000") @PathVariable UUID userId,
            @Valid @RequestBody UpdateRequest request) {
        return ResponseEntity.ok(infoUpdateUsersUseCase.execute(request, userId));
    }

    @Operation(summary = "Actualizar propios datos", description = "Usuario autenticado actualiza su propia información. Requiere autenticación.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualizado correctamente", content = @Content(schema = @Schema(implementation = UserUpdateResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "No autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/update/me")
    public ResponseEntity<ProfileUpdate> updateUser(
            @Valid @RequestBody UpdateRequest request,
            Authentication authentication) {
        UUID userId = (UUID) authentication.getPrincipal();
        return ResponseEntity.ok(infoUpdateUsersUseCase.execute(request, userId));
    }

    @Operation(summary = "Cambiar estado de usuario", description = "Bloquea o activa un usuario. Requiere rol ADMIN.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado actualizado exitosamente", content = @Content(schema = @Schema(implementation = StatusUserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "No autenticado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "No autorizado (Requiere ADMIN)", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StatusUserResponse> setStatus(@Valid @RequestBody StatusUserRequest request) {
        return ResponseEntity.ok(setStatusUserUseCase.execute(request.userId(), request.status()));
    }
}
