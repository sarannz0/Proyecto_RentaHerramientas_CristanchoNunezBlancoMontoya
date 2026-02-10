package com.bkseducate.securityapp.domain.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.ToolReport;

public interface ToolReportRepository {
    List<ToolReport> findAll();
    Optional<ToolReport> findById(UUID id);    
    ToolReport save(ToolReport toolReport);
    void delete(UUID id);
}
