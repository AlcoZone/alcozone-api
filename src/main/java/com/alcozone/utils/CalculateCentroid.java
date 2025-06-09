package com.alcozone.utils;

import com.alcozone.domain.model.MinifiedCrash;

import java.util.List;

public class CalculateCentroid {
    public static Double[] execute(List<MinifiedCrash> crashes) {
        double averageLatitude = crashes.stream().mapToDouble(MinifiedCrash::getLatitude).average().orElse(0);
        double averageLongitude = crashes.stream().mapToDouble(MinifiedCrash::getLongitude).average().orElse(0);
        return new Double[]{averageLatitude, averageLongitude};
    }
}
