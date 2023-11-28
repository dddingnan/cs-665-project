package edu.bu.met.cs665.calculator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FlightFuelCostCalculatorTest {

    @Test
    public void testFuelCostCalculation() {
        double fuelConsumption = 1000; // Example fuel consumption in gallons
        FlightFuelCostCalculator calculator = new FlightFuelCostCalculator(fuelConsumption);

        double actualCost = calculator.call();
        double expectedCost = 1000 * 4.15; // Expected cost based on fuel consumption and cost per gallon

        assertEquals("The calculated fuel cost should match the expected value.",
                expectedCost, actualCost, 0.01); // Allow for a small margin of error
    }
}
