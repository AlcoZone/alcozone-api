package com.alcozone.application.service;

import java.util.List;
import java.util.UUID;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.models.Accident;
import com.alcozone.domain.repository.AccidentRepository;
import com.alcozone.application.dto.accident.CreateAccidentDTO;

@ApplicationScoped
public class AccidentService {

    @Inject AccidentRepository accidentRepository;

    public Accident saveAccident(CreateAccidentDTO createAccidentDTO) {
        Accident accident = createAccidentDTO.toDomain();
        accident.setUuid(UUID.randomUUID().toString());
        return accidentRepository.saveAccident(accident, createAccidentDTO.getRevisionEntity());
    }

    public List<Accident> getAccidentsByRevisionUuid(String revisionUuid) {
        return accidentRepository.findAccidentsByRevisionUuid(revisionUuid);
    }
}
