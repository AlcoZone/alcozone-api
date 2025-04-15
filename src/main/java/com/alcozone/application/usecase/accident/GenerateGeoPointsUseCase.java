package com.alcozone.application.usecase.accident;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.application.service.AccidentService;
import com.alcozone.domain.models.GeoPoint;

@ApplicationScoped
public class GenerateGeoPointsUseCase {

    @Inject AccidentService accidentService;

    public List<GeoPoint> execute(String uuid) {
        return accidentService.getAccidentsByRevisionUuid(uuid).stream()
            .map(accident -> new GeoPoint(
                accident.getLatitude(),
                accident.getLongitude(),
                accident.getDate() + " " + accident.getHour()
            ))
            .toList();
    }
}
