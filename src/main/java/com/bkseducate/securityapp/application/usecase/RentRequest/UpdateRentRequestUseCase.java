package com.bkseducate.securityapp.application.usecase.RentRequest;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bkseducate.securityapp.domain.model.Rent;
import com.bkseducate.securityapp.domain.model.RentRequest;
import com.bkseducate.securityapp.domain.model.RentRequestStatus;
import com.bkseducate.securityapp.domain.model.RentStatus;
import com.bkseducate.securityapp.domain.ports.RentRepository;
import com.bkseducate.securityapp.domain.ports.RentRequestRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateRentRequestUseCase {
    private final RentRequestRepository rentRequestRepository;
    private final RentRepository rentRepository;

    @Transactional
    public void execute(UUID rentRequesId, RentRequestStatus status) {

        RentRequest rentRequest = rentRequestRepository.findById(rentRequesId)
            .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar el RENT REQUEST con ID " + rentRequesId));

        Rent rent = rentRequest.getRent();
        if (status.equals(RentRequestStatus.ACCEPTED)) {
            rent.updateStatus(RentStatus.RENTED);
            rentRepository.save(rent);
        } else {
            rent.updateStatus(RentStatus.CANCELED);
            rentRepository.save(rent);
        }
    }
}
