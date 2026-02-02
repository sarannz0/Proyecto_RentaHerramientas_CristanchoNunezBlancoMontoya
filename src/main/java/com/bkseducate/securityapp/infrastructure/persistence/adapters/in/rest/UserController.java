package com.bkseducate.securityapp.infrastructure.persistence.adapters.in.rest;

import com.bkseducate.securityapp.application.dto.AssignRoleRequest;
import com.bkseducate.securityapp.application.dto.RegisterRequest;
import com.bkseducate.securityapp.application.dto.UserResponse;
import com.bkseducate.securityapp.application.dto.UserUpdateRequest;
import com.bkseducate.securityapp.application.dto.UserUpdateResponse;
import com.bkseducate.securityapp.application.usecase.Role.AssignRoleUseCase;
import com.bkseducate.securityapp.application.usecase.User.CreateSupplierUseCase;
import com.bkseducate.securityapp.application.usecase.User.GetUsersForAdminUseCase;
import com.bkseducate.securityapp.application.usecase.User.InfoUpdateUsersUseCase;
import com.bkseducate.securityapp.domain.exceptions.InfoNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "Gestión de Usuarios", description = "Endpoints para administración de usuarios y roles")
@RestController
@RequestMapping("/users")
public class UserController {
    
    private final AssignRoleUseCase assignRoleUseCase;
    private final CreateSupplierUseCase createSupplierUseCase;
    private final GetUsersForAdminUseCase getUsersForAdminUseCase;
    private final InfoUpdateUsersUseCase infoUpdateUsersUseCase;
    
    public UserController(
        InfoUpdateUsersUseCase infoUpdateUsersUseCase,
        GetUsersForAdminUseCase getUsersForAdminUseCase,
        AssignRoleUseCase assignRoleUseCase,
        CreateSupplierUseCase createSupplierUseCase
    ) {
        this.infoUpdateUsersUseCase = infoUpdateUsersUseCase;
        this.getUsersForAdminUseCase = getUsersForAdminUseCase;
        this.createSupplierUseCase = createSupplierUseCase;
        this.assignRoleUseCase = assignRoleUseCase;
    }
    
    @Operation(
        summary = "Asignar rol a usuario",
        description = "Asigna un rol específico a un usuario. Requiere rol ADMIN."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rol asignado exitosamente",
            content = @Content(schema = @Schema(implementation = UserResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos (requiere ADMIN)"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{userId}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> assignRole(
            @PathVariable UUID userId,
            @Valid @RequestBody AssignRoleRequest request) {
        UserResponse response = assignRoleUseCase.execute(userId, request);
        return ResponseEntity.ok(response);
    }

    @Operation(
        summary = "Registrar Proveedor en la App",
        description = "Registra los usuarios de tipo Proveedor (SUPPLIER). Requiere rol ADMIN."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proveedor registrado exitosamente",
            content = @Content(schema = @Schema(implementation = UserResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos (requiere ADMIN)"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/suppliers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> createSupplier(
        @Valid @RequestBody RegisterRequest request
    ) {
        UserResponse user = createSupplierUseCase.execute(request);
        return ResponseEntity.ok(user);
    }  

    @Operation(
        summary = "Listar usuarios.",
        description = "Devuelve una lista de los usuarios registrados como USER o SUPPLIER. Requiere rol ADMIN."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuarios Listados exitosamente",
            content = @Content(schema = @Schema(implementation = UserResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos (requiere ADMIN)"),
        @ApiResponse(responseCode = "404", description = "Usuarios no encontrados")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> listUsers() {
        return ResponseEntity.ok(getUsersForAdminUseCase.execute());
    }

    @Operation(
        summary = "Actualizar datos de usuario por el ADMIN",
        description = "Actualiza informacion no sensible de cada usuario. Requiere Token de autenticación."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Informacion actualizada correctamente",
            content = @Content(schema = @Schema(implementation = UserUpdateResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos (requiere rol ADMIN)"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/update/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserUpdateResponse> updateUserByAdmin(
        @PathVariable UUID userId,
        @Valid @RequestBody UserUpdateRequest request) {
        return ResponseEntity.ok(infoUpdateUsersUseCase.execute(request, userId));
    }
    

    @Operation(
        summary = "Actualizar datos de usuario",
        description = "Actualiza informacion no sensible de cada usuario. Requiere Token de autenticación."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Informacion actualizada correctamente",
            content = @Content(schema = @Schema(implementation = UserUpdateResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos (requiere Token de autenticación)"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/update/me")
    public ResponseEntity<UserUpdateResponse> updateUser(
        @Valid @RequestBody UserUpdateRequest request,
        Authentication authentication
    ) {
        UUID userId = (UUID) authentication.getPrincipal();
        return ResponseEntity.ok(infoUpdateUsersUseCase.execute(request, userId));
    }
}
