package com.bkseducate.securityapp.domain.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.ReporteDano;

public interface ReporteDanoRepository {
    List<ReporteDano> findAll();
    Optional<ReporteDano> findById(UUID id);    
    ReporteDano save(ReporteDano toolReport);
    void delete(UUID id);
}
