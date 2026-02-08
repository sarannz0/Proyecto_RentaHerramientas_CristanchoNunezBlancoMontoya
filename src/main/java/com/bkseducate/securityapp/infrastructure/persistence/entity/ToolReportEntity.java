package com.bkseducate.securityapp.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tools_reports")
public class ToolReportEntity {
    @Id
    @Column(columnDefinition = "CHAR(36)", length = 36)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tool_item_id", nullable = false, updatable = false)
    private ToolItemEntity toolItemEntity;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private UserEntity userEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rent_id", nullable = false, updatable = false)
    private RentEntity rentEntity;

    public ToolReportEntity() {
    }

    public ToolReportEntity(UUID id, ToolItemEntity toolItemEntity, String description, LocalDateTime date,
            UserEntity userEntity, RentEntity rentEntity) {
        this.id = id;
        this.toolItemEntity = toolItemEntity;
        this.description = description;
        this.date = date;
        this.userEntity = userEntity;
        this.rentEntity = rentEntity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ToolItemEntity getToolItemEntity() {
        return toolItemEntity;
    }

    public void setToolItemEntity(ToolItemEntity toolItemEntity) {
        this.toolItemEntity = toolItemEntity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public RentEntity getRentEntity() {
        return rentEntity;
    }

    public void setRentEntity(RentEntity rentEntity) {
        this.rentEntity = rentEntity;
    }

}
