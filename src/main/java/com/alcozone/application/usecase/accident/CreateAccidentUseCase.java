package com.alcozone.application.usecase.accident;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.models.Accident;
import com.alcozone.application.service.AccidentService;
import com.alcozone.application.dto.accident.CreateAccidentDTO;

@ApplicationScoped
public class CreateAccidentUseCase {

    @Inject AccidentService accidentService;

    public Accident execute(CreateAccidentDTO createAccidentDTO) {
        return accidentService.saveAccident(createAccidentDTO);
    }
}