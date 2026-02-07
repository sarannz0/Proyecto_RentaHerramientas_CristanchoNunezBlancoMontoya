package com.bkseducate.securityapp.application.usecase.Address;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.domain.model.Address;
import com.bkseducate.securityapp.domain.ports.CityRepository;
import com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence.AddressRepositoryAdapter;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressCreateUpdateUseCase {
    
    private final AddressRepositoryAdapter addressRepository;
    private final CityRepository cityRepositoryPort;

    @Transactional
    public Address execute(Address request) {
        cityRepositoryPort.findById(request.getCity().getId()).orElseThrow(
            () ->  new EntityNotFoundException("No se encontro la ciudad")
        );
        Address savedAddress = addressRepository.save(
            Address.create(request.getAddress(), request.getPostalCode(), request.getCity())
        );
        return savedAddress;
    }

    @Transactional
    public Address execute(UUID id,Address request) {
        cityRepositoryPort.findById(request.getCity().getId()).orElseThrow(
            () ->  new EntityNotFoundException("No se encontro la ciudad")
        );
        Address savedAddress = addressRepository.update(id,
            Address.create(request.getAddress(), request.getPostalCode(), request.getCity())
        );
        return savedAddress;
    }
}
