package com.bkseducate.securityapp.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Invoice {

    private UUID id;
    private Rent rent;
    private BigDecimal totalAmount;
    private LocalDateTime date;

    private Invoice() {
    }

    public static Invoice create(
        Rent rent,
        BigDecimal totalAmount
    ) {
        Invoice invoice = new Invoice();
        invoice.id = UUID.randomUUID();
        invoice.rent = rent;
        invoice.totalAmount = totalAmount;
        invoice.date = LocalDateTime.now();

        return invoice;
    }

    public static Invoice recreate(
        UUID id,
        Rent rent,
        BigDecimal totalAmount,
        LocalDateTime date
    ) {
        Invoice invoice = new Invoice();
        invoice.id = id;
        invoice.rent = rent;
        invoice.totalAmount = totalAmount;
        invoice.date = date;

        return invoice;
    }

    public UUID getId() {
        return id;
    }

    public Rent getRentId() {
        return rent;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
