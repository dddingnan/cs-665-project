package edu.bu.met.cs665.weather;

public class Weather<T> {
    private T season;
    private double windSpeed;
    private double temperature;
    private double humidity;

    public Weather() {
    }

    public Weather(T season, double windSpeed, double temperature, double humidity) {
        this.season = season;
        this.windSpeed = windSpeed;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    // Getters and setters for the weather indices

    public T getSeason() {
        return season;
    }

    public void setSeason(T season) {
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
