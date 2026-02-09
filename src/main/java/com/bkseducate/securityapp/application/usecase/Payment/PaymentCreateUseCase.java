package com.bkseducate.securityapp.application.usecase.Payment;

import java.util.UUID;
import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.domain.exceptions.UserNotFoundException;
import com.bkseducate.securityapp.domain.model.Invoice;
import com.bkseducate.securityapp.domain.model.Payment;
import com.bkseducate.securityapp.domain.model.PaymentMethod;
import com.bkseducate.securityapp.domain.model.Rent;
import com.bkseducate.securityapp.domain.ports.InvoiceRepository;
import com.bkseducate.securityapp.domain.ports.PaymentRepository;
import com.bkseducate.securityapp.domain.ports.RentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentCreateUseCase {
    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;
    private final RentRepository rentRepository;

    public void execute(UUID userId, UUID rentId, PaymentMethod method) {

        Rent rent = rentRepository.findByID(rentId)
            .orElseThrow(() -> new UserNotFoundException("No se pudo encontrar la RENTA con ID " +  rentId));

        if (!rent.getUser().getId().equals(userId)) throw new RuntimeException("No tienes permisos para acceder a este recurso");

        invoiceRepository.save(Invoice.create(rent, rent.getTotalAmount()));
        paymentRepository.save(Payment.create(rent, rent.getTotalAmount(), method));
    }
}