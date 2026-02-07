package com.bkseducate.securityapp.application.usecase.User;
import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.application.dto.Profile.SupplierResponse;
import com.bkseducate.securityapp.application.dto.Supplier.SupplierRequest;
import com.bkseducate.securityapp.application.usecase.Address.AddressCreateUpdateUseCase;
import com.bkseducate.securityapp.domain.exceptions.DomainException;
import com.bkseducate.securityapp.domain.model.Address;
import com.bkseducate.securityapp.domain.model.City;
import com.bkseducate.securityapp.domain.model.Country;
import com.bkseducate.securityapp.domain.model.Role;
import com.bkseducate.securityapp.domain.model.SupplierM;
import com.bkseducate.securityapp.domain.model.User;
import com.bkseducate.securityapp.domain.ports.CityRepository;
import com.bkseducate.securityapp.domain.ports.CountryRepository;
import com.bkseducate.securityapp.domain.ports.PasswordService;
import com.bkseducate.securityapp.domain.ports.RoleRepository;
import com.bkseducate.securityapp.domain.ports.SupplierRepository;
import com.bkseducate.securityapp.domain.ports.UserRepository;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.SupplierMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CreateSupplierUseCase {
    
    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final RoleRepository roleRepository;
    private final SupplierMapper supplierMapper;
    private final AddressCreateUpdateUseCase addressCreateUseCase;
    private final SupplierRepository supplierRepository;
    private final CityRepository cityRepositoryPort;
    private final CountryRepository cRepositoryPort;

    public CreateSupplierUseCase(
        UserRepository userRepository,
        PasswordService passwordService,
        RoleRepository roleRepository,
        SupplierMapper supplierMapper,
        AddressCreateUpdateUseCase addressCreateUseCase,
        SupplierRepository supplierRepository,
        CityRepository cityRepositoryPort,
        CountryRepository cRepositoryPort
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

    @Transactional
    public SupplierResponse execute(SupplierRequest request) {
        if (userRepository.existsByEmail(request.email()))
            throw new DomainException("El email "+request.email()+ "ya esta registrado");

        String hashedPassword = passwordService.hash(request.password());
        User user = User.create(request.name(), request.email(), hashedPassword);

        Country savedCountry = cRepositoryPort.findByIsocode(request.countryIsocode())
            .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar el pais"));

        City savedCity = cityRepositoryPort.findByName(request.cityName().trim().toUpperCase())
            .orElseGet(() -> cityRepositoryPort.save(City.create(request.cityName().trim().toUpperCase(), savedCountry)));


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
