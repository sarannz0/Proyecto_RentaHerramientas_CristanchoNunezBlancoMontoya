package com.bkseducate.securityapp.infrastructure.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.EstadoDevolucion;
import com.bkseducate.securityapp.domain.model.RentStatus;
import com.bkseducate.securityapp.domain.model.ToolItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "rents")
public class RentEntity {

    @Id
    @Column(columnDefinition = "CHAR(36)", length = 36)
    private UUID id;

   @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tool_item_id", nullable = true, updatable = false)
    private Set<ToolItemEntity> toolItemEntity = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RentStatus rentStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoDevolucion estadoDevolucion;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressEntity addressEntity;

    public RentEntity(UUID id, LocalDateTime startDate, LocalDateTime endDate, BigDecimal totalAmount,
            Set<ToolItemEntity> toolItemEntity, RentStatus rentStatus, EstadoDevolucion estadoDevolucion, UserEntity userEntity, AddressEntity addressEntity) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalAmount = totalAmount;
        this.toolItemEntity = toolItemEntity;
        this.rentStatus = rentStatus;
        this.estadoDevolucion = estadoDevolucion;
        this.userEntity = userEntity;
        this.addressEntity = addressEntity;
    }

    public RentEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Set<ToolItemEntity> getToolItemEntity() {
        return toolItemEntity;
    }

    public void setToolItemEntity(Set<ToolItemEntity> toolItemEntity) {
        this.toolItemEntity = toolItemEntity;
    }

    public RentStatus getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(RentStatus rentStatus) {
        this.rentStatus = rentStatus;
    }
    
    public EstadoDevolucion getEstadoDevolucion() {
        return estadoDevolucion;
    }

    public void setEstadoDevolucion(EstadoDevolucion estadoDevolucion) {
        this.estadoDevolucion = estadoDevolucion;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }



}
