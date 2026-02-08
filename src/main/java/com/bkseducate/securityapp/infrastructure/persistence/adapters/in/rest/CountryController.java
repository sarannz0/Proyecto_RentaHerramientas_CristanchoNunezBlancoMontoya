package com.bkseducate.securityapp.infrastructure.persistence.adapters.in.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence.CountryRepositoryAdapter;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    CountryRepositoryAdapter countryRepositoryAdapter;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UUID getPais(@PathVariable UUID id) {
        return countryRepositoryAdapter.findById(id).get().getId();
    }
    
}
