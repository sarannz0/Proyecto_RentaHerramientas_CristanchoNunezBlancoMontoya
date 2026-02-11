package com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bkseducate.securityapp.domain.model.ReporteDano;
import com.bkseducate.securityapp.domain.ports.ReporteDanoRepository;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.ReporteDanoMapper;
import com.bkseducate.securityapp.infrastructure.persistence.repository.ReporteDanoJpaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReporteDanoRepositoryAdapter implements ReporteDanoRepository {

    private final ReporteDanoJpaRepository jpaRepository;
    private final ReporteDanoMapper reporteDanoMapper;

    @Override
    public List<ReporteDano> findAll() {
        return jpaRepository.findAll().stream().map(reporteDanoMapper::toDomain).toList();
    }
    @Override
    public Optional<ReporteDano> findById(UUID id) {
        return jpaRepository.findById(id).map(reporteDanoMapper::toDomain);
    }
    @Override
    public ReporteDano save(ReporteDano toolReport) {
        return reporteDanoMapper.toDomain(jpaRepository.save(reporteDanoMapper.toEntity(toolReport)));
    }
    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    }
}
