package com.alcozone.application.service;

import com.alcozone.domain.model.Crash;
import com.alcozone.domain.model.MinifiedCrash;
import com.alcozone.domain.model.Roadblock;
import com.alcozone.utils.DbscanRunner;
import com.alcozone.utils.SimpleLinearRegression;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@ApplicationScoped
public class PredictionService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private int scoreFromCount(int count) {
        if (count <= 0) return 1;
        if (count >= 20) return 10;
        return Math.min(10, Math.max(1, (int)Math.round((count / 20.0) * 10)));
    }

    private Double[] calculateCentroid(List<MinifiedCrash> crashes) {
        double sumLat = 0;
        double sumLon = 0;
        for (MinifiedCrash crash : crashes) {
            sumLat += crash.getLatitude();
            sumLon += crash.getLongitude();
        }
        return new Double[]{sumLat / crashes.size(), sumLon / crashes.size()};
    }

    public Map<String, List<Roadblock>> predictRoadblocks(
            List<MinifiedCrash> crashes,
            double epsilonMeters,
            int minPoints,
            int startHour,
            int endHour) {

        Map<String, List<Roadblock>> predictionsPerDay = new HashMap<>();

        for (DayOfWeek day : DayOfWeek.values()) {
            List<MinifiedCrash> filtered = crashes.stream()
                    .map(c -> new AbstractMap.SimpleEntry<>(c, LocalDateTime.parse(c.getDatetime(), FORMATTER)))
                    .filter(entry -> entry.getValue().getDayOfWeek() == day)
                    .filter(entry -> {
                        int hour = entry.getValue().getHour();
                        return hour >= startHour && hour <= endHour;
                    })
                    .map(Map.Entry::getKey)
                    .toList();

            if (filtered.size() < minPoints) continue;

            Map<Integer, List<MinifiedCrash>> clusters = DbscanRunner.generateClusters(filtered, epsilonMeters, minPoints);

            List<Roadblock> dayPredictions = new ArrayList<>();

            for (List<MinifiedCrash> clusterPoints : clusters.values()) {
                Double[] centroid = calculateCentroid(clusterPoints);

                Map<Integer, Integer> crashCountsByWeek = new TreeMap<>();
                for (MinifiedCrash crash : clusterPoints) {
                    LocalDateTime dt = LocalDateTime.parse(crash.getDatetime(), FORMATTER);
                    int weekNum = dt.get(java.time.temporal.IsoFields.WEEK_OF_WEEK_BASED_YEAR);
                    crashCountsByWeek.put(weekNum, crashCountsByWeek.getOrDefault(weekNum, 0) + 1);
                }

                if (crashCountsByWeek.size() < 2) {
                    int totalCrashes = clusterPoints.size();
                    int score = scoreFromCount(totalCrashes);
                    dayPredictions.add(new Roadblock(centroid[0], centroid[1], totalCrashes, score));
                    continue;
                }

                double[] weeks = new double[crashCountsByWeek.size()];
                double[] counts = new double[crashCountsByWeek.size()];
                int idx = 0;
                int maxWeek = Integer.MIN_VALUE;
                for (Map.Entry<Integer, Integer> entry : crashCountsByWeek.entrySet()) {
                    weeks[idx] = entry.getKey();
                    counts[idx] = entry.getValue();
                    if (entry.getKey() > maxWeek) maxWeek = entry.getKey();
                    idx++;
                }

                SimpleLinearRegression regression = new SimpleLinearRegression();
                regression.fit(weeks, counts);
                double predictedCrashes = regression.predict(maxWeek + 1);
                int expectedCrashes = (int) Math.max(0, Math.round(predictedCrashes));
                int score = scoreFromCount(expectedCrashes);

                dayPredictions.add(new Roadblock(centroid[0], centroid[1], expectedCrashes, score));
            }

            predictionsPerDay.put(day.toString().toLowerCase(), dayPredictions);
        }

        return predictionsPerDay;
    }
}
