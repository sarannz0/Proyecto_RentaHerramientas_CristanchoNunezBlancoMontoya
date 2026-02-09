package com.bkseducate.securityapp.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.domain.model.Rent;
import com.bkseducate.securityapp.domain.model.RentRequest;
import com.bkseducate.securityapp.infrastructure.persistence.entity.RentRequestEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RentRequestMapper {
    
    private final RentMapper rentMapper;

    public RentRequest toDomain(RentRequestEntity entity) {
        return RentRequest.recreate(
            entity.getId(),
            rentMapper.toDomain(entity.getRent()),
            entity.getCreatedAt(),
            entity.getStatus()
        );
    }

    public RentRequestEntity toEntity(RentRequest rent) {
        return new RentRequestEntity(
            rent.getId(),
            rentMapper.toEntity(rent.getRent()),
            rent.getCreatedAt(),
            rent.getStatus()
        );
    }
}
