package com.alcozone.application.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.alcozone.domain.models.*;
import com.alcozone.utils.DbscanRunner;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.repository.RevisionRepository;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;

@ApplicationScoped
public class RevisionService {
    //TODO Move all processing logic for Crashes to Revision

    @Inject RevisionRepository revisionRepository;

    private Double[] calculateCentroid(List<Crash> crashes) {
        double averageLatitude = crashes.stream().mapToDouble(Crash::getLatitude).average().orElse(0);
        double averageLongitude = crashes.stream().mapToDouble(Crash::getLongitude).average().orElse(0);
        return new Double[]{averageLatitude, averageLongitude};
    }

    public Revision getRevision(String uuid) {
        return revisionRepository.getRevision(uuid);
    }

    public RevisionEntity getRevisionEntity(String uuid) {
        return revisionRepository.getRevisionEntity(uuid);
    }

    public Revision saveRevision(String name){
        Revision revision = new Revision();
        revision.setUuid(UUID.randomUUID().toString());
        revision.setName(name);
        return revisionRepository.saveRevision(revision);
    }

    public List<Cluster> clusterizeRevision(List<Crash> crashes, double epsilonMeters, int minPoints){
        Map<Integer, List<Crash>> unprocessedClusters = DbscanRunner.generateClusters(crashes, epsilonMeters, minPoints);

        return unprocessedClusters.entrySet().stream()
            .map(entry -> new Cluster(entry.getKey(), calculateCentroid(entry.getValue())))
            .toList();
    }

    public Map<String, List<Roadblock>> predictRoadblocks(List<Crash> crashes, double epsilonMeters, int minPoints, int startHour, int endHour) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        Map<String, List<Roadblock>> roadblocksPerDay = new HashMap<>();

        for (DayOfWeek day : DayOfWeek.values()) {
            List<Crash> filtered = crashes.stream()
                    .map(point -> new AbstractMap.SimpleEntry<>(point, LocalDateTime.parse(point.getDatetime(), formatter)))
                    .filter(entry -> entry.getValue().getDayOfWeek() == day)
                    .filter(entry -> {
                        int hour = entry.getValue().getHour();
                        return hour >= startHour && hour <= endHour;
                    })
                    .map(Map.Entry::getKey)
                    .toList();

            if (filtered.size() < minPoints) continue;

            Map<Integer, List<Crash>> clusters = DbscanRunner.generateClusters(filtered, epsilonMeters, minPoints);

            List<Roadblock> dailySummaries = new ArrayList<>();
            for (List<Crash> clusterPoints : clusters.values()) {
                double avgLat = clusterPoints.stream().mapToDouble(Crash::getLatitude).average().orElse(0);
                double avgLon = clusterPoints.stream().mapToDouble(Crash::getLongitude).average().orElse(0);
                dailySummaries.add(new Roadblock(avgLat, avgLon, clusterPoints.size()));
            }

            roadblocksPerDay.put(day.toString().toLowerCase(), dailySummaries);
        }

        return roadblocksPerDay;
    }
}