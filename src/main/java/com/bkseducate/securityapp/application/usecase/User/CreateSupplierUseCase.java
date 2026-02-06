package com.bkseducate.securityapp.application.usecase.User;

import java.util.Currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.application.dto.Profile.SupplierResponse;
import com.bkseducate.securityapp.application.dto.Supplier.SupplierRequest;
import com.bkseducate.securityapp.application.mapper.SupplierMapper;
import com.bkseducate.securityapp.application.usecase.Address.AddressCreateUseCase;
import com.bkseducate.securityapp.domain.exceptions.DomainException;
import com.bkseducate.securityapp.domain.model.Address;
import com.bkseducate.securityapp.domain.model.City;
import com.bkseducate.securityapp.domain.model.Country;
import com.bkseducate.securityapp.domain.model.Role;
import com.bkseducate.securityapp.domain.model.SupplierM;
import com.bkseducate.securityapp.domain.model.User;
import com.bkseducate.securityapp.domain.ports.CityRepositoryPort;
import com.bkseducate.securityapp.domain.ports.CountryRepositoryPort;
import com.bkseducate.securityapp.domain.ports.PasswordService;
import com.bkseducate.securityapp.domain.ports.RoleRepository;
import com.bkseducate.securityapp.domain.ports.SupplierRepository;
import com.bkseducate.securityapp.domain.ports.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CreateSupplierUseCase {
    
    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final RoleRepository roleRepository;
    private final SupplierMapper supplierMapper;
    private final AddressCreateUseCase addressCreateUseCase;
    private final SupplierRepository supplierRepository;
    private final CityRepositoryPort cityRepositoryPort;
    private final CountryRepositoryPort cRepositoryPort;

    public CreateSupplierUseCase(
        UserRepository userRepository,
        PasswordService passwordService,
        RoleRepository roleRepository,
        SupplierMapper supplierMapper,
        AddressCreateUseCase addressCreateUseCase,
        SupplierRepository supplierRepository,
        CityRepositoryPort cityRepositoryPort,
        CountryRepositoryPort cRepositoryPort
    ) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.roleRepository = roleRepository;
        this.supplierMapper = supplierMapper;
        this.addressCreateUseCase = addressCreateUseCase;
        this.supplierRepository = supplierRepository;
        this.cityRepositoryPort = cityRepositoryPort;
        this.cRepositoryPort = cRepositoryPort;
    }

    public SupplierResponse execute(SupplierRequest request) {
        if (userRepository.existsByEmail(request.email()))
            throw new DomainException("El email "+request.email()+ "ya esta registrado");

        String hashedPassword = passwordService.hash(request.password());
        User user = User.create(request.name(), request.email(), hashedPassword);

        Country savedCountry = cRepositoryPort.findByIsocode(request.countryIsocode())
            .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar el pais"));
        System.out.println("================================ COUNTRY " + savedCountry.getId());

        City savedCity = cityRepositoryPort.save(City.create(request.cityName(), savedCountry));

        Address savedAddress = addressCreateUseCase.execute(
            Address.create(request.addressDesc(), request.postalCode(), savedCity)
        );
        
        Role role = roleRepository.findByName("SUPPLIER")
            .orElseThrow(() -> new RuntimeException("No se encontro el ROL SUPPLIER"));
        user.assignRole(role);

        SupplierM supplier = SupplierM.create(user.getId(), request.companyName(), savedAddress);

        SupplierM savedSupplier = supplierRepository.save(supplier);
        User savedUser = userRepository.save(user);
        return supplierMapper.toResponse(savedUser, savedSupplier);
    }
}
