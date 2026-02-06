package com.bkseducate.securityapp.application.usecase.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.domain.model.Address;
import com.bkseducate.securityapp.domain.ports.CityRepositoryPort;
import com.bkseducate.securityapp.infrastructure.persistence.adapters.out.persistence.AddressRepositoryAdapter;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AddressCreateUseCase {
    @Autowired 
    AddressRepositoryAdapter addressRepository;
    CityRepositoryPort cityRepositoryPort;

    public Address execute(Address request) {
        if (cityRepositoryPort.findById(request.getCity().getId()) == null) 
            throw new EntityNotFoundException("No se encontro la ciudad");

        Address savedAddress = addressRepository.save(
            Address.create(request.getAddress(), request.getPostalCode(), request.getCity())
        );
        return savedAddress;
    }
}
