package com.bkseducate.securityapp.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Invoice {

    private UUID id;
    private UUID rentId;
    private BigDecimal totalAmount;
    private LocalDateTime date;

    private Invoice() {
    }

    public static Invoice create(
        UUID rentId,
        BigDecimal totalAmount
    ) {
        Invoice invoice = new Invoice();
        invoice.id = UUID.randomUUID();
        invoice.rentId = rentId;
        invoice.totalAmount = totalAmount;
        invoice.date = LocalDateTime.now();

        return invoice;
    }

    public static Invoice recreate(
        UUID id,
        UUID rentId,
        BigDecimal totalAmount,
        LocalDateTime date
    ) {
        Invoice invoice = new Invoice();
        invoice.id = id;
        invoice.rentId = rentId;
        invoice.totalAmount = totalAmount;
        invoice.date = date;

        return invoice;
    }

    public UUID getId() {
        return id;
    }

    public UUID getRentId() {
        return rentId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
