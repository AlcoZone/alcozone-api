package com.alcozone.application.service;

import com.alcozone.domain.classes.Accident;
import com.alcozone.domain.repository.AccidentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class AccidentService {

    @Inject
    AccidentRepository accidentRepository;

    public void saveAccident(String date, String hour, String type, String subType, String reportedBy, String town, String neighbourhood, Double latitude, Double longitude) {
        Accident accident = new Accident();
        accident.setUuid(UUID.randomUUID().toString());
        accident.setDate(date);
        accident.setHour(hour);
        accident.setType(type);
        accident.setSubType(subType);
        accident.setReportedBy(reportedBy);
        accident.setTown(town);
        accident.setNeighbourhood(neighbourhood);
        accident.setLatitude(latitude);
        accident.setLongitude(longitude);

        accidentRepository.saveAccident(accident);
    }
}
