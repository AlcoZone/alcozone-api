package com.alcozone.application.service;

import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.*;

import jakarta.enterprise.context.ApplicationScoped;

import com.alcozone.domain.models.GeoPoint;
import com.alcozone.domain.models.Roadblock;
import com.alcozone.utils.DbscanRunner;

@ApplicationScoped
public class CrashClusteringService {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Map<String, List<Roadblock>> predictHotspotSummaries(List<GeoPoint> crashes, double epsilonMeters, int minPoints, int startHour, int endHour) {
        Map<String, List<Roadblock>> summariesByDay = new HashMap<>();

        for (DayOfWeek day : DayOfWeek.values()) {
            List<GeoPoint> filtered = crashes.stream()
                    .map(point -> new AbstractMap.SimpleEntry<>(point, LocalDateTime.parse(point.getTimestamp(), formatter)))
                    .filter(entry -> entry.getValue().getDayOfWeek() == day)
                    .filter(entry -> {
                        int hour = entry.getValue().getHour();
                        return hour >= startHour && hour <= endHour;
                    })
                    .map(Map.Entry::getKey)
                    .toList();

            if (filtered.size() < minPoints) continue;

            Map<Integer, List<GeoPoint>> clusters = DbscanRunner.generateClusters(filtered, epsilonMeters, minPoints);

            List<Roadblock> dailySummaries = new ArrayList<>();
            for (List<GeoPoint> clusterPoints : clusters.values()) {
                double avgLat = clusterPoints.stream().mapToDouble(GeoPoint::getLatitude).average().orElse(0);
                double avgLon = clusterPoints.stream().mapToDouble(GeoPoint::getLongitude).average().orElse(0);
                dailySummaries.add(new Roadblock(avgLat, avgLon, clusterPoints.size()));
            }

            summariesByDay.put(day.toString(), dailySummaries);
        }

        return summariesByDay;
    }
}
