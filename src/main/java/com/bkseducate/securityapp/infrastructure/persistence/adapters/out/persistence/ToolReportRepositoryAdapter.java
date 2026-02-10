package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.domain.model.ToolReport;
import com.bkseducate.securityapp.domain.ports.ToolReportRepository;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.ToolReportMapper;
import com.bkseducate.securityapp.infrastructure.persistence.repository.ToolReportJpaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ToolReportRepositoryAdapter implements ToolReportRepository{

    private ToolReportJpaRepository jpaRepository;
    private ToolReportMapper toolReportMapper;

    @Override
    public List<ToolReport> findAll() {
        return jpaRepository.findAll().stream().map(toolReportMapper::toDomain).toList();
    }
    @Override
    public Optional<ToolReport> findById(UUID id) {
        return jpaRepository.findById(id).map(toolReportMapper::toDomain);
    }
    @Override
    public ToolReport save(ToolReport toolReport) {
        return toolReportMapper.toDomain(jpaRepository.save(toolReportMapper.toEntity(toolReport)));
    }
    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    }
}
