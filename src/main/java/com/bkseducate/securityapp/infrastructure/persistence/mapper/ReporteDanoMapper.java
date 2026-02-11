package com.bkseducate.securityapp.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.domain.model.ReporteDano;
import com.bkseducate.securityapp.domain.model.ToolReport;
import com.bkseducate.securityapp.infrastructure.persistence.entity.ReporteDanoEntity;
import com.bkseducate.securityapp.infrastructure.persistence.entity.ToolReportEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReporteDanoMapper {
    private final ToolItemMapper toolItemMapper;
    private final RentMapper rentMapper;
    private final UserMapper userMapper;

    public ReporteDano toDomain(ReporteDanoEntity entity) {
        return ReporteDano.recreate(
            entity.getId(),
            entity.getDescription(),
            entity.getDate()
        );
    }

    public ReporteDanoEntity toEntity(ReporteDano domain) {
        return new ReporteDanoEntity(
            domain.getId(), 
            domain.getDescription(),
            domain.getDate(),
        );
    }
}
