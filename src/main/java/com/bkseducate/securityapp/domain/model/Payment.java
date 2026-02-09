package com.bkseducate.securityapp.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {

    private UUID id;
    private Rent rent;
    private BigDecimal amount;
    private LocalDateTime date;
    private PaymentMethod method;

    private Payment() {
    }

    public static Payment create(
        Rent rent,
        BigDecimal amount,
        PaymentMethod method
    ) {
        Payment payment = new Payment();
        payment.id = UUID.randomUUID();
        payment.rent = rent;
        payment.amount = amount;
        payment.date = LocalDateTime.now();
        payment.method = method;
        return payment;
    }

    public static Payment recreate(
        UUID id,
        Rent rent,
        BigDecimal amount,
        LocalDateTime date,
        PaymentMethod method
    ) {
        Payment payment = new Payment();
        payment.id = id;
        payment.rent = rent;
        payment.amount = amount;
        payment.date = date;
        payment.method = method;
        return payment;
    }

    public UUID getId() {
        return id;
    }

    public Rent getRentId() {
        return rent;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public PaymentMethod getMethod() {
        return method;
    }
}
