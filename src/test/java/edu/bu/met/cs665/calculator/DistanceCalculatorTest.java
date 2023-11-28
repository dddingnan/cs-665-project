package edu.bu.met.cs665.calculator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DistanceCalculatorTest {

    @Test
    public void testDistanceCalculation() {
        // Example coordinates (latitude and longitude) for two locations
        double latitude1 = 40.7128; // New York City
        double longitude1 = -74.0060;
        double latitude2 = 34.0522; // Los Angeles
        double longitude2 = -118.2437;

        DistanceCalculator calculator = new DistanceCalculator(latitude1, longitude1, latitude2, longitude2);

        double actualDistance = calculator.call();
        double expectedDistance = 3940; // Approximate distance between New York City and Los Angeles in kilometers

        assertEquals("The calculated distance should match the expected value.",
                expectedDistance, actualDistance, 50.0); // Allow for a margin of error
    }
}
