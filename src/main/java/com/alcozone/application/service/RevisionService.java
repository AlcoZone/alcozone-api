package com.alcozone.application.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import com.alcozone.domain.models.*;
import com.alcozone.infrastructure.dto.revision.response.RevisionListItemDTO;
import com.alcozone.utils.DbscanRunner;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.repository.RevisionRepository;
import com.alcozone.infrastructure.persistence.revision.RevisionEntity;

@ApplicationScoped
public class RevisionService {

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

        return unprocessedClusters.values().stream()
            .map(cluster -> {
                Double[] centroid = calculateCentroid(cluster);
                return new Cluster(centroid[0], centroid[1], cluster.size());
            })
        .toList();
    }

    public List<RevisionListItemDTO> getAllRevisions() {
        return revisionRepository.getAllRevisions().stream().map(rev -> {
            RevisionListItemDTO dto = new RevisionListItemDTO();
            dto.setUuid(rev.getUuid());
            dto.setName(rev.getName());
            dto.setDate(rev.getCreated_at().toLocalDate().toString()); // convierte a YYYY-MM-DD
            dto.setDataQuantity(rev.getCrashes() != null ? rev.getCrashes().size() : 0);
            return dto;
        }).collect(Collectors.toList());
    }

    public Map<String, List<Cluster>> predictRoadblocks(List<Crash> crashes, double epsilonMeters, int minPoints, int startHour, int endHour) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        Map<String, List<Cluster>> roadblocksPerDay = new HashMap<>();

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

            List<Cluster> roadblocks = new ArrayList<>();
            for (List<Crash> clusterPoints : clusters.values()) {
                Double[] centroid = calculateCentroid(clusterPoints);
                roadblocks.add(new Cluster(centroid[0], centroid[1], clusterPoints.size()));
            }

            roadblocksPerDay.put(day.toString().toLowerCase(), roadblocks);
        }

        return roadblocksPerDay;
    }
}