/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: Location.java
 * Description: Represents a geographic location.
 * This class encapsulates the details of a geographic location, including its name,
 * latitude, and longitude. It ensures that the latitude and longitude values are within
 * valid ranges.
 */
package edu.bu.met.cs665.location;

import edu.bu.met.cs665.exception.InvalidDataException;

public class Location {
    private String name;
    private double latitude;
    private double longitude;

    /**
     * Constructs a new Location object.
     * Validates the latitude and longitude values to ensure they are within the
     * valid range.
     * Latitude must be between -90 and 90 degrees, and longitude must be between
     * -180 and 180 degrees.
     *
     * @param name      The name of the location.
     * @param latitude  The latitude of the location in degrees.
     * @param longitude The longitude of the location in degrees.
     * @throws InvalidDataException if the latitude or longitude values are outside
     *                              their valid ranges.
     */
    public Location(String name, double latitude, double longitude) throws InvalidDataException {
        if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
            throw new InvalidDataException("Invalid geolocation data for " + name, "Geolocation",
                    "Latitude: " + latitude + ", Longitude:" + longitude);
        }
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Gets the name of the location.
     *
     * @return The name of the location.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the latitude of the location.
     *
     * @return The latitude of the location in degrees.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Gets the longitude of the location.
     *
     * @return The longitude of the location in degrees.
     */
    public double getLongitude() {
        return longitude;
    }
}
