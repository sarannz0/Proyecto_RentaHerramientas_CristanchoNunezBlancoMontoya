package com.bkseducate.securityapp.infrastructure.persistence.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.domain.model.Rent;
import com.bkseducate.securityapp.infrastructure.persistence.entity.RentEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RentMapper {
    private final UserMapper userMapper;
    private final ToolItemMapper toolItemMapper;
    private final AddressMapper addressMapper;

    public Rent toDomain(RentEntity entity) {
        return Rent.recreate(entity.getId(),
            entity.getStartDate(),
            entity.getEndDate(),
            entity.getTotalAmount(),
            entity.getToolItemEntity().stream().map(toolItemMapper::toDomain).collect(Collectors.toSet()),
            entity.getRentStatus(),
            entity.getEstadoDevolucion(),
            userMapper.toDomain(entity.getUserEntity()),
            addressMapper.toDomain(entity.getAddressEntity())
        );
    }

    public RentEntity toEntity(Rent rent) {
        return new RentEntity(
            rent.getId(),
            rent.getStartDate(),
            rent.getEndDate(),
            rent.getTotalAmount(),
            rent.getToolItem().stream().map(toolItemMapper::toEntity).collect(Collectors.toSet()),
            rent.getStatus(),
            rent.getEstadoDevolucion(),         
            userMapper.toEntity(rent.getUser()),
            addressMapper.toEntity(rent.getAddress())
        );
    }

}
