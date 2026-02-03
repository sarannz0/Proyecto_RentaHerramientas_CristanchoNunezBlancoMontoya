package com.bkseducate.securityapp.domain.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bkseducate.securityapp.domain.model.Tool;

public interface ToolRepository {
    List<Tool> findAll();
    Optional<Tool> findById(UUID id);
    Tool save(Tool tool);
    Tool update(UUID id, Tool tool);
    void delete(UUID id);
}
