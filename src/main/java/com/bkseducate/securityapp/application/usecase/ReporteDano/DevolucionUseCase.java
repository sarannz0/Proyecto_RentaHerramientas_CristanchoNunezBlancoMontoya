package com.bkseducate.securityapp.application.usecase.ReporteDano;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bkseducate.securityapp.application.dto.reporte_danos.DevolucionDTO;
import com.bkseducate.securityapp.domain.model.Rent;
import com.bkseducate.securityapp.domain.model.ReporteDano;
import com.bkseducate.securityapp.domain.model.ReporteDanoEnum;
import com.bkseducate.securityapp.domain.ports.RentRepository;
import com.bkseducate.securityapp.domain.ports.ReporteDanoRepository;
import com.bkseducate.securityapp.domain.ports.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DevolucionUseCase {
    private final ReporteDanoRepository reporteDanoRepository;
    private final RentRepository rentRepository;
    private final UserRepository userRepository;

    @Transactional
    public void execute(UUID rentId, DevolucionDTO request) {
        
        Rent rent = rentRepository.findByID(rentId)
            .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar el alquiler con ID " + rentId));
        
        System.out.println(request.estado());

        if (request.estado().equals("OK")) rent.updateEstadoDevolucion(ReporteDanoEnum.DEVUELTO_OK);

        if (request.estado().equals("DAÑOS")) rent.updateEstadoDevolucion(ReporteDanoEnum.DEVUELTO_CON_DAÑOS);
        rentRepository.save(rent);

        ReporteDano reporteDano = ReporteDano.create(
            request.descripcion(),
            request.costoReparacion(),
            rent
        );

        reporteDanoRepository.save(reporteDano);
    }

}
