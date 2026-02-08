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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tool/item")
@RequiredArgsConstructor
public class ToolItemController {        
    
    private final ToolItemCreateUseCase toolItemCreateUseCase;
    private final ToolITemDeleteUseCase toolITemDeleteUseCase;
    private final ToolITemGetAllUseCase toolITemGetAllUseCase;
    private final ToolItemUpdateStatusUseCase toolItemUpdateStatusUseCase;
    private final ToolItemUpdateAvaiableUseCase toolItemUpdateAvaiableUseCase;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/create/{catalogId}")
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<HttpStatus> create(
        @PathVariable UUID catalogId,
        Authentication authentication
    ) {
        UUID supplierId = (UUID) authentication.getPrincipal();
        toolItemCreateUseCase.execute(catalogId, supplierId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/delete/{itemId}")
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<HttpStatus> delete(
        @PathVariable UUID itemId,
        Authentication authentication
    ) {
        UUID supplierId = (UUID) authentication.getPrincipal();
        toolITemDeleteUseCase.execute(itemId, supplierId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/get/{catalogId}")
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<List<ToolItem>> getAll(
        @PathVariable UUID catalogId,
        Authentication authentication
    ) {
        UUID supplierId = (UUID) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(toolITemGetAllUseCase.execute(catalogId, supplierId));
    }
    
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/update/status/{itemId}")
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<HttpStatus> updateStatus(
        @PathVariable UUID itemId,
        Authentication authentication,
        @Valid @RequestBody ToolUpdateStatusRequest status
    ) {
        UUID supplierId = (UUID) authentication.getPrincipal();
        toolItemUpdateStatusUseCase.execute(itemId, status.status(), supplierId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/update/avaible/{itemId}/{bool}")
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<HttpStatus> updateAvaiable(
        @PathVariable UUID itemId,
        @PathVariable Boolean bool,
        Authentication authentication
    ) {
        UUID supplierId = (UUID) authentication.getPrincipal();
        toolItemUpdateAvaiableUseCase.execute(itemId, bool, supplierId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
