package edu.bu.met.cs665.calculator;

import java.util.concurrent.Callable;

public class DistanceCalculator implements Callable<Double> {

    private final double latitude1;
    private final double longitude1;
    private final double latitude2;
    private final double longitude2;

    public DistanceCalculator(double latitude1, double longitude1, double latitude2, double longitude2) {
        this.latitude1 = latitude1;
        this.longitude1 = longitude1;
        this.latitude2 = latitude2;
        this.longitude2 = longitude2;
    }

    @Override
    public Double call() {
        // Haversine formula
        // https://en.wikipedia.org/wiki/Haversine_formula
        int earthRadius = 6371; // Radius of the earth in km

        double latDifference = Math.toRadians(latitude2 - latitude1);
        double lonDifference = Math.toRadians(longitude2 - longitude1);

        double lat1Radians = Math.toRadians(latitude1);
        double lat2Radians = Math.toRadians(latitude2);

        double sinLatDifferenceSquared = Math.sin(latDifference / 2) * Math.sin(latDifference / 2);
        double sinLonDifferenceSquared = Math.sin(lonDifference / 2) * Math.sin(lonDifference / 2);

        double haversineA = sinLatDifferenceSquared
                + Math.cos(lat1Radians) * Math.cos(lat2Radians) * sinLonDifferenceSquared;
        double haversineC = 2 * Math.atan2(Math.sqrt(haversineA), Math.sqrt(1 - haversineA));

        return earthRadius * haversineC; // Distance in km
    }
}
