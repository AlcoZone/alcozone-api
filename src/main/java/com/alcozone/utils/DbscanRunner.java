package com.alcozone.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alcozone.domain.model.MinifiedCrash;
import smile.clustering.DBSCAN;

public class DbscanRunner {

    public static Map<Integer, List<MinifiedCrash>> generateClusters(List<MinifiedCrash> crashes, double epsilonMeters, int minPoints) {
        DBSCAN<MinifiedCrash> dbscan = DBSCAN.fit(crashes.toArray(new MinifiedCrash[0]), new HaversineDistance(), minPoints, epsilonMeters);

        Map<Integer, List<MinifiedCrash>> clusters = new HashMap<>();
        for (int i = 0; i < dbscan.y.length; i++) {
            int label = dbscan.y[i];
            if (label == -1) continue;

            clusters.computeIfAbsent(label, l -> new ArrayList<>()).add(crashes.get(i));
        }
        return clusters;
    }
}

