package edu.bu.met.cs665.weather;

import edu.bu.met.cs665.season.Season;

public class Weather {
    private Season season;
    private double windSpeed;
    private double temperature;
    private double humidity;

    public Weather() {
    }

    public Weather(Season season, double windSpeed, double temperature, double humidity) {
        this.season = season;
        this.windSpeed = windSpeed;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    // Getters and setters for the weather indices

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}
