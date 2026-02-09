package com.bkseducate.securityapp.infrastructure.persistence.adapters.in.rest;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkseducate.securityapp.application.usecase.Payment.PaymentCreateUseCase;
import com.bkseducate.securityapp.domain.model.PaymentMethod;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pay")
@RequiredArgsConstructor
public class PaymentController {
    
    private final PaymentCreateUseCase paymentCreateUseCase;

    @PostMapping("/{rendId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> createPayment(
        @PathVariable UUID rentId,
        Authentication authentication,
        PaymentMethod method
    ) {
        UUID userId = (UUID) authentication.getPrincipal();
        paymentCreateUseCase.execute(userId, rentId, method);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

}
