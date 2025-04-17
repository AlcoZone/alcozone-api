package com.alcozone.application.usecase.crash;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.application.service.CrashService;
import com.alcozone.domain.models.GeoPoint;

@ApplicationScoped
public class GenerateGeoPointsUseCase {
    //TODO Move this UseCase to RevisionService due to the only Usage for this UseCase its for the Generation of The Clusterize and Prediction

    @Inject
    CrashService crashService;

    public List<GeoPoint> execute(String uuid) {
        return crashService.getCrashesByRevisionUuid(uuid).stream()
            .map(crash -> new GeoPoint(crash.getLatitude(), crash.getLongitude(), crash.getDate() + " " + crash.getHour()))
            .toList();
    }
}
