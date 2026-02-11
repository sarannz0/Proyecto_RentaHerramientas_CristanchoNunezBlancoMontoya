package com.bkseducate.securityapp.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.domain.model.ReporteDano;
import com.bkseducate.securityapp.infrastructure.persistence.entity.ReporteDanoEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReporteDanoMapper {
    private final RentMapper rentMapper;

    public ReporteDano toDomain(ReporteDanoEntity entity) {
        return ReporteDano.recreate(
            entity.getId(),
            entity.getDescription(),
            entity.getRepairCost(),
            entity.getDate(),
            rentMapper.toDomain(entity.getRent())
        );
    }

    public ReporteDanoEntity toEntity(ReporteDano domain) {
        return new ReporteDanoEntity(
            domain.getId(), 
            domain.getDescription(),
            domain.getRepairCost(),
            domain.getDate(),
            rentMapper.toEntity(domain.getRent())
        );
    }
}
