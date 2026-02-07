package com.bkseducate.securityapp.application.usecase.User;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.application.dto.Profile.updateProfile.ProfileUpdate;
import com.bkseducate.securityapp.application.dto.Profile.updateProfile.SupplierUpdateRequest;
import com.bkseducate.securityapp.application.dto.Profile.updateProfile.UpdateRequest;
import com.bkseducate.securityapp.application.dto.Profile.updateProfile.UserUpdateRequest;
import com.bkseducate.securityapp.application.dto.Profile.updateProfile.UserUpdateResponse;
import com.bkseducate.securityapp.application.usecase.Address.AddressCreateUpdateUseCase;
import com.bkseducate.securityapp.domain.exceptions.UserNotFoundException;
import com.bkseducate.securityapp.domain.model.Address;
import com.bkseducate.securityapp.domain.model.City;
import com.bkseducate.securityapp.domain.model.Country;
import com.bkseducate.securityapp.domain.model.SupplierM;
import com.bkseducate.securityapp.domain.model.User;
import com.bkseducate.securityapp.domain.ports.CityRepository;
import com.bkseducate.securityapp.domain.ports.CountryRepository;
import com.bkseducate.securityapp.domain.ports.SupplierRepository;
import com.bkseducate.securityapp.domain.ports.UserRepository;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.SupplierMapper;
import com.bkseducate.securityapp.infrastructure.persistence.mapper.UserMapper;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InfoUpdateUsersUseCase {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SupplierMapper supplierMapper;
    private final SupplierRepository supplierRepository;
    private final CityRepository cityRepositoryPort;
    private final CountryRepository cRepository;
    private final AddressCreateUpdateUseCase addressCreateUseCase;

    public InfoUpdateUsersUseCase(
        UserRepository userRepository,
        UserMapper userMapper,
        SupplierRepository supplierRepository,
        SupplierMapper supplierMapper,
        CityRepository cityRepositoryPort,
        CountryRepository cRepository,
        AddressCreateUpdateUseCase addressCreateUseCase
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.supplierMapper = supplierMapper;
        this.supplierRepository = supplierRepository;
        this.cityRepositoryPort = cityRepositoryPort;
        this.cRepository = cRepository;
        this.addressCreateUseCase = addressCreateUseCase;
    }   

    public ProfileUpdate execute(UpdateRequest request, UUID id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("Usuario con ID: "+ id +" no encontrado"));
        user.updateInfo(request.name(), request.email());
        userRepository.save(user);

        ProfileUpdate profile = request.profile();

        if (profile instanceof SupplierUpdateRequest s) {
            SupplierM supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No se pudo encontrar el usuario con ID "+id));

            Country savedCountry = cRepository.findByIsocode(request.countryIsocode())
                .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar el pais"));

            City savedCity = cityRepositoryPort.findByName(request.cityName().trim().toUpperCase())
                .orElseGet(() -> cityRepositoryPort.save(City.create(request.cityName().trim().toUpperCase(), savedCountry)));

            Address savedAddress = addressCreateUseCase.execute(id,
                Address.create(request.addressDesc(), request.postalCode(), savedCity)
            );

            supplier.updateInfo(s.companyName(), savedAddress);
            supplierRepository.update(id, supplierMapper.toRequest(user, supplier));
            return supplierMapper.toUpdateResponse(user, supplier);
        }

        return userMapper.toUpdateResponse(user);
    }
}
