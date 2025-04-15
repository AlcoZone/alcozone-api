package com.alcozone.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import smile.clustering.DBSCAN;

import com.alcozone.domain.models.GeoPoint;

public class DbscanRunner {

    public static Map<Integer, List<GeoPoint>> generateClusters(List<GeoPoint> crashes, double epsilonMeters, int minPoints) {
        DBSCAN<GeoPoint> dbscan = DBSCAN.fit(crashes.toArray(new GeoPoint[0]), new HaversineDistance(), minPoints, epsilonMeters);

        Map<Integer, List<GeoPoint>> clusters = new HashMap<>();
        for (int i = 0; i < dbscan.y.length; i++) {
            int label = dbscan.y[i];
            if (label == -1) continue;

            clusters.computeIfAbsent(label, l -> new ArrayList<>())
                    .add(crashes.get(i));
        }

        return clusters;
    }
}

