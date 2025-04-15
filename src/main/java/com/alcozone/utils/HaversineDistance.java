package com.alcozone.utils;

import smile.math.distance.Distance;

import com.alcozone.domain.models.GeoPoint;

public class HaversineDistance implements Distance<GeoPoint> {
    private static final double EARTH_RADIUS = 6371e3;

    @Override
    public double d(GeoPoint a, GeoPoint b) {
        double latitudePoint1 = Math.toRadians(a.getLatitude());
        double longitudePoint1 = Math.toRadians(a.getLongitude());
        double latitudePoint2 = Math.toRadians(b.getLatitude());
        double longitudePoint2 = Math.toRadians(b.getLongitude());

        double deltaLatitude = latitudePoint2 - latitudePoint1;
        double deltaLongitude = longitudePoint2 - longitudePoint1;

        double haversine = Math.sin(deltaLatitude / 2) * Math.sin(deltaLatitude / 2)
                + Math.cos(latitudePoint1) * Math.cos(latitudePoint2)
                * Math.sin(deltaLongitude / 2) * Math.sin(deltaLongitude / 2);

        return 2 * EARTH_RADIUS * Math.asin(Math.sqrt(haversine));
    }
}
