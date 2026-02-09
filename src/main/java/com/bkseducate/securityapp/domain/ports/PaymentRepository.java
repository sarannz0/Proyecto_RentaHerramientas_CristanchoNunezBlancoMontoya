package com.bkseducate.securityapp.domain.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.Payment;

public interface PaymentRepository{
    List<Payment> findAll();
    Optional<Payment> findById(UUID id);
    Payment save(Payment payment);
    void delete(UUID id);
}
