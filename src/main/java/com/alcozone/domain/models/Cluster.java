package com.alcozone.domain.models;

import lombok.Data;

import java.util.List;

@Data
public class Cluster {
    private int id;
    private Double[] centroid;
    private List<GeoPoint> accidents;

    private void calculateCentroid() {
        double latAvg = accidents.stream().mapToDouble(GeoPoint::getLatitude).average().orElse(0);
        double lonAvg = accidents.stream().mapToDouble(GeoPoint::getLatitude).average().orElse(0);
        centroid = new Double[]{latAvg, lonAvg};
    }

    public Cluster(int id, List<GeoPoint> accidents) {
        this.id = id;
        this.accidents = accidents;
        calculateCentroid();
    }
}