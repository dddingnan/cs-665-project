package edu.bu.met.cs665.calculator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CO2EmissionsCalculatorTest {

    @Test
    public void testCO2EmissionsCalculation() {
        double fuelConsumption = 100; // Example fuel consumption in gallons
        CO2EmissionsCalculator calculator = new CO2EmissionsCalculator(fuelConsumption);

        double expectedEmissions = fuelConsumption * 10.1; // Expected CO2 emissions
        double actualEmissions = calculator.call();

        assertEquals("The calculated CO2 emissions should match the expected value.",
                expectedEmissions, actualEmissions, 0.001); // Allow for a small margin of error
    }
}
