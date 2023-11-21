package edu.bu.met.cs665.location;

import edu.bu.met.cs665.exception.InvalidDataException;

public class Location {
    private String name;
    private double latitude;
    private double longitude;

    public Location(String name, double latitude, double longitude) throws InvalidDataException {
        if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
            throw new InvalidDataException("Invalid geolocation data for " + name, "Geolocation",
                    "Latitude: " + latitude + ", Longitude:" + longitude);
        }
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
