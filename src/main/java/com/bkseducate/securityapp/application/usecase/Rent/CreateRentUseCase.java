package com.bkseducate.securityapp.application.usecase.Rent;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bkseducate.securityapp.application.dto.Rent.RentCreateRequest;
import com.bkseducate.securityapp.application.usecase.Address.AddressCreateUpdateUseCase;
import com.bkseducate.securityapp.application.usecase.ToolItem.ToolItemUpdateStatusUseCase;
import com.bkseducate.securityapp.domain.exceptions.InvalidInfoException;
import com.bkseducate.securityapp.domain.exceptions.UserNotFoundException;
import com.bkseducate.securityapp.domain.model.Address;
import com.bkseducate.securityapp.domain.model.City;
import com.bkseducate.securityapp.domain.model.Country;
import com.bkseducate.securityapp.domain.model.Rent;
import com.bkseducate.securityapp.domain.model.ToolCatalog;
import com.bkseducate.securityapp.domain.model.ToolItem;
import com.bkseducate.securityapp.domain.model.ToolItemStatus;
import com.bkseducate.securityapp.domain.model.User;
import com.bkseducate.securityapp.domain.ports.CityRepository;
import com.bkseducate.securityapp.domain.ports.CountryRepository;
import com.bkseducate.securityapp.domain.ports.InvoiceRepository;
import com.bkseducate.securityapp.domain.ports.PaymentRepository;
import com.bkseducate.securityapp.domain.ports.RentRepository;
import com.bkseducate.securityapp.domain.ports.ToolCatalogRepository;
import com.bkseducate.securityapp.domain.ports.ToolItemRepository;
import com.bkseducate.securityapp.domain.ports.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateRentUseCase {
    
    private final RentRepository rentRepository;
    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;
    private final ToolItemUpdateStatusUseCase toolItemUpdateStatusUseCase;
    private final ToolCatalogRepository toolCatalogRepository;
    private final ToolItemRepository toolItemRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final UserRepository userRepository;
    private final AddressCreateUpdateUseCase addressCreateUseCase;

    @Transactional
    public Rent execute(UUID userId, RentCreateRequest request) {
        BigDecimal amount = BigDecimal.valueOf(0);
        long hoursBetween = ChronoUnit.HOURS.between(request.startDate(), request.endDate());
        long hours = Math.max(1, hoursBetween);

        if (request.startDate().isBefore(LocalDateTime.now()) || request.endDate().isBefore(LocalDateTime.now())) 
            throw new InvalidInfoException("No puedes reservar dias anteriores");
        if (request.startDate().isAfter(request.endDate())) 
            throw new InvalidInfoException("La fecha de inicio debe ser menor a la fecha final");


        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("No se pudo encontrar el usuario con ID " + userId));
        
        Country savedCountry = countryRepository.findByIsocode(request.countryIsocode())
            .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar el pais"));

        String cityName = request.cityName().trim().toUpperCase();
        City savedCity = cityRepository.findByName(cityName)
            .orElseGet(() -> cityRepository.save(City.create(cityName, savedCountry)));
        
        Address savedAddress = addressCreateUseCase.execute(
            Address.create(request.addressDesc(), request.postalCode(), savedCity)
        );

        Rent rent = Rent.create(
            request.startDate(),
            request.endDate(),
            user,
            savedAddress
        );
        
        for (UUID p : request.productsId()) {
            ToolCatalog toolCatalog = toolCatalogRepository.findById(p)
                .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar el ITEM con ID " + p));

            ToolItem toolItem = toolItemRepository.findAllByToolCatalogEntityIdByToolItemStatus(toolCatalog.getId(), ToolItemStatus.AVAILABLE).stream().findFirst()
                .orElseThrow(() -> new EntityNotFoundException("No hay items disponibles, o no se pudo encontrar el catalogo con nombre " + toolCatalog.getName()));    
            
            toolItemUpdateStatusUseCase.execute(toolItem.getId(), ToolItemStatus.UNAVAILABLE, toolItem.getToolCatalog().getSupplier().getUserId());
            rent.assignTool(toolItem);
            amount = amount.add(toolCatalog.getPrice());
        };
        
        amount = amount.multiply(BigDecimal.valueOf(hours));
        rent.updateAmount(amount);
        return rentRepository.save(rent);
    }
}




