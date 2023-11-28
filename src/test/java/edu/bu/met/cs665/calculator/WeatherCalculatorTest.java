package edu.bu.met.cs665.calculator;

import org.junit.Assert;
import org.junit.Test;
import edu.bu.met.cs665.season.Season;
import edu.bu.met.cs665.weather.Weather;

public class WeatherCalculatorTest {

    @Test
    public void testSpringWeatherFactor() throws Exception {
        Weather springWeather = new Weather(Season.SPRING, 55, 26, 50);
        WeatherCalculator calculator = new WeatherCalculator(springWeather);

        // Execute
        double factor = calculator.call();

        // Assert
        Assert.assertTrue("Weather factor should be reduced in Spring with high wind and temperature", factor < 1.0);
    }

    @Test
    public void testSummerWeatherFactor() throws Exception {
        // Create a Weather instance for Summer with specific conditions
        Weather summerWeather = new Weather(Season.SUMMER, 45, 31, 45);
        WeatherCalculator calculator = new WeatherCalculator(summerWeather);

        // Execute
        double factor = calculator.call();

        // Assert
        Assert.assertTrue("Weather factor should be significantly reduced in Summer with high wind and temperature",
                factor < 0.8);
    }

}
