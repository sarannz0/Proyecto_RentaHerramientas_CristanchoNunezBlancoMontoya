package com.bkseducate.securityapp.application.usecase.Payment;

import java.util.UUID;
import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.domain.exceptions.UserNotFoundException;
import com.bkseducate.securityapp.domain.model.Invoice;
import com.bkseducate.securityapp.domain.model.Payment;
import com.bkseducate.securityapp.domain.model.PaymentMethod;
import com.bkseducate.securityapp.domain.model.Rent;
import com.bkseducate.securityapp.domain.model.RentRequest;
import com.bkseducate.securityapp.domain.model.User;
import com.bkseducate.securityapp.domain.ports.InvoiceRepository;
import com.bkseducate.securityapp.domain.ports.PaymentRepository;
import com.bkseducate.securityapp.domain.ports.RentRepository;
import com.bkseducate.securityapp.domain.ports.RentRequestRepository;
import com.bkseducate.securityapp.domain.ports.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentCreateUseCase {
    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;
    private final RentRequestRepository rentRequestRepository;
    private final RentRepository rentRepository;
    private final UserRepository userRepository;

    public void execute(UUID userId, UUID rentId, PaymentMethod method) {

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("No se pudo encontrar el usuario con ID " +  userId));

        Rent rent = rentRepository.findByID(rentId)
            .orElseThrow(() -> new UserNotFoundException("No se pudo encontrar la RENTA con ID " +  rentId));

        

        if (!user.getId().equals(userId)) throw new RuntimeException("No tienes permisos para acceder a este recurso");

        invoiceRepository.save(Invoice.create(rent, rent.getTotalAmount()));
        rentRequestRepository.save(RentRequest.create(rent));
        paymentRepository.save(Payment.create(rent, rent.getTotalAmount(), method));
    }
}