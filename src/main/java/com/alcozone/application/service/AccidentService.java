package com.alcozone.application.service;

import com.alcozone.domain.Accident;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class AccidentService {


    public Accident createAccident(String date, String hour, String town, String neighbourhood, String type, String subType, String reportedBy, Double latitude, Double longitude) {
        UUID uuid = UUID.randomUUID();
        return new Accident(
                uuid.toString(),
                date,
                hour,
                town,
                neighbourhood,
                type,
                subType,
                reportedBy,
                latitude,
                longitude
        );

    }
}
