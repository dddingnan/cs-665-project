/**
 * Name: Dingnan Hsu
 * Course: CS-665 Software Designs & Patterns
 * Date: 11/21/2023
 * File Name: WeatherCalculator.java
 * Description: 
 * The WeatherCalculator class is designed to calculate a weather factor based on various weather conditions.
 * It implements the Callable interface, allowing it to be used with ExecutorService for concurrent execution.
 * This calculator takes into account seasonal variations and specific weather parameters like wind speed, temperature, and humidity.
 */
package edu.bu.met.cs665.calculator;

import java.util.concurrent.Callable;

import edu.bu.met.cs665.season.Season;
import edu.bu.met.cs665.weather.Weather;

public class WeatherCalculator implements Callable<Double> {
    private Weather weather;

    /**
     * Constructs a WeatherCalculator with a specified Weather object.
     * 
     * @param weather The Weather object containing the details required for the
     *                calculation.
     */
    public WeatherCalculator(Weather weather) {
        this.weather = weather;
    }

    /**
     * Calculates the weather factor based on the season and weather conditions.
     * The method adjusts the weather factor based on wind speed, temperature, and
     * humidity for each season.
     * Pre-condition: The weather object must not be null.
     * Post-condition: Returns a weather factor that is a valid value between 0 and
     * 2.
     * 
     * @return The calculated weather factor.
     * @throws AssertionError If the weather object is null or the calculated factor
     *                        is out of the expected range.
     */
    @Override
    public Double call() {
        // Pre-condition: The weather object must not be null
        assert weather != null : "Weather object cannot be null";

        double weatherFactor = 1.0;

        Season season = weather.getSeason();
        double windSpeed = weather.getWindSpeed();
        double temperature = weather.getTemperature();
        double humidity = weather.getHumidity();
        // Adjust the weather factor based on the weather indices and season
        if (season == Season.SPRING) {
            // Adjust the weather factor for Spring season based on wind speed, temperature,
            // and humidity
            if (windSpeed > 50 && temperature > 25 && humidity < 60) {
                weatherFactor *= 0.8; // Reduce the weather factor by 20%
            } else if (windSpeed < 10 && temperature < 15 && humidity > 70) {
                weatherFactor *= 1.2; // Increase the weather factor by 20%
            }
        } else if (season == Season.SUMMER) {
            // Adjust the weather factor for Summer season based on wind speed, temperature,
            // and humidity
            if (windSpeed > 40 && temperature > 30 && humidity < 50) {
                weatherFactor *= 0.7; // Reduce the weather factor by 30%
            } else if (windSpeed < 20 && temperature < 25 && humidity > 60) {
                weatherFactor *= 1.1; // Increase the weather factor by 10%
            }
        } else if (season == Season.AUTUMN) {
            // Adjust the weather factor for Autumn season based on wind speed, temperature,
            // and humidity
            if (windSpeed > 30 && temperature > 20 && humidity < 55) {
                weatherFactor *= 0.9; // Reduce the weather factor by 10%
            } else if (windSpeed < 15 && temperature < 20 && humidity > 65) {
                weatherFactor *= 1.05; // Increase the weather factor by 5%
            }
        } else if (season == Season.WINTER) {
            // Adjust the weather factor for Winter season based on wind speed, temperature,
            // and humidity
            if (windSpeed > 60 && temperature < 5 && humidity < 40) {
                weatherFactor *= 0.6; // Reduce the weather factor by 40%
            } else if (windSpeed < 30 && temperature > -5 && humidity > 50) {
                weatherFactor *= 1.3; // Increase the weather factor by 30%
            }
        }

        // Post-condition: The calculated weather factor must be a valid value between 0
        // and 2
        assert weatherFactor >= 0 && weatherFactor <= 2 : "Invalid weather factor";

        // Return the calculated weather factor
        return weatherFactor;
    }
}
