package com.bkseducate.securityapp.infrastructure.persistence.adapters.in.rest;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkseducate.securityapp.application.dto.Rent.RentCreateRequest;
import com.bkseducate.securityapp.application.usecase.Rent.CreateRentUseCase;
import com.bkseducate.securityapp.domain.model.Rent;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rent")
@RequiredArgsConstructor
public class RentController {
    private final CreateRentUseCase createRentUseCase;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<Rent> createRent(
        @Valid @RequestBody RentCreateRequest request,
        Authentication authentication
    ) {
        UUID userId = (UUID) authentication.getPrincipal();
        return ResponseEntity.ok(createRentUseCase.execute(userId, request));
    }
}
