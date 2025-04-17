package com.alcozone.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import smile.clustering.DBSCAN;

import com.alcozone.domain.models.Crash;

public class DbscanRunner {

    public static Map<Integer, List<Crash>> generateClusters(List<Crash> crashes, double epsilonMeters, int minPoints) {
        DBSCAN<Crash> dbscan = DBSCAN.fit(crashes.toArray(new Crash[0]), new HaversineDistance(), minPoints, epsilonMeters);

        Map<Integer, List<Crash>> clusters = new HashMap<>();
        for (int i = 0; i < dbscan.y.length; i++) {
            int label = dbscan.y[i];
            if (label == -1) continue;

            clusters.computeIfAbsent(label, l -> new ArrayList<>()).add(crashes.get(i));
        }
        return clusters;
    }
}

