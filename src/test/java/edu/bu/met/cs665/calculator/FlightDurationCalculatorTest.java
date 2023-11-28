package edu.bu.met.cs665.calculator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import edu.bu.met.cs665.airplane.Airplane;

public class FlightDurationCalculatorTest {

    @Test
    public void testFlightDurationCalculation() {
        // Mock Airplane with a fixed speed
        Airplane mockAirplane = new Airplane() {
            @Override
            public String getName() {
                return "Mock Airplane";
            }

            @Override
            public double getRange() {
                return 5000;
            }

            @Override
            public double getFuelCapacity() {
                return 2000;
            }

            @Override
            public double getFuelBurnRate() {
                return 100;
            }

            @Override
            public double getFuelConsumption() {
                return 5;
            }

            @Override
            public double getSpeed() {
                return 800;
            } // 800 km/h
        };

        double distance = 1600; // Distance in kilometers
        FlightDurationCalculator calculator = new FlightDurationCalculator(mockAirplane, distance);

        double actualDuration = calculator.call();
        double expectedDuration = 2; // Expected duration in hours for 1600 km at 800 km/h

        assertEquals("The calculated flight duration should match the expected value.",
                expectedDuration, actualDuration, 0.1); // Allow for a small margin of error
    }
}
