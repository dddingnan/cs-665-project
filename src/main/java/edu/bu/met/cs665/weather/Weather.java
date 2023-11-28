/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: Weather.java
 * Description: Represents weather conditions for a specific location or time.
 * It includes information about the season, wind speed, temperature, and humidity.
 */
package edu.bu.met.cs665.weather;

import edu.bu.met.cs665.season.Season;

public class Weather {
    private Season season;
    private double windSpeed;
    private double temperature;
    private double humidity;

    /**
     * Constructs a new Weather object with specified parameters.
     *
     * @param season      The current season.
     * @param windSpeed   The wind speed in the area.
     * @param temperature The temperature in the area.
     * @param humidity    The humidity level in the area.
     */
    public Weather(Season season, double windSpeed, double temperature, double humidity) {
        this.season = season;
        this.windSpeed = windSpeed;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    /**
     * Gets the current season.
     *
     * @return The current season.
     */
    public Season getSeason() {
        return season;
    }

    /**
     * Sets the current season.
     *
     * @param season The season to set.
     */
    public void setSeason(Season season) {
        this.season = season;
    }

    /**
     * Gets the wind speed.
     *
     * @return The wind speed.
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * Sets the wind speed.
     *
     * @param windSpeed The wind speed to set.
     */
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * Gets the temperature.
     *
     * @return The temperature.
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Sets the temperature.
     *
     * @param temperature The temperature to set.
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * Gets the humidity.
     *
     * @return The humidity.
     */
    public double getHumidity() {
        return humidity;
    }

    /**
     * Sets the humidity.
     *
     * @param humidity The humidity to set.
     */
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}
