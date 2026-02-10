package com.bkseducate.securityapp.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.domain.model.ToolReport;
import com.bkseducate.securityapp.infrastructure.persistence.entity.ToolReportEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ToolReportMapper {
    private final ToolItemMapper toolItemMapper;
    private final RentMapper rentMapper;
    private final UserMapper userMapper;

    public ToolReport toDomain(ToolReportEntity entity) {
        return ToolReport.recreate(
            entity.getId(), 
            toolItemMapper.toDomain(entity.getToolItemEntity()),
            userMapper.toDomain(entity.getUserEntity()),
            rentMapper.toDomain(entity.getRentEntity()),
            entity.getDescription(),
            entity.getDate()
        );
    }

    public ToolReportEntity toEntity(ToolReport domain) {
        return new ToolReportEntity(
            domain.getId(), 
            toolItemMapper.toEntity(domain.getToolItem()),
            domain.getDescription(),
            domain.getDate(),
            userMapper.toEntity(domain.getUserId()),
            rentMapper.toEntity(domain.getRentId())
        );
    }
}
