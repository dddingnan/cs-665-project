package edu.bu.met.cs665.calculator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import edu.bu.met.cs665.airplane.AirplaneTestModel;

public class FuelConsumptionCalculatorTest {

    @Test
    public void testFuelConsumptionCalculation() {
        double distance = 500; // Example distance in miles
        double weatherFactor = 1.2; // Example weather factor
        double fuelConsumptionRate = 50; // Example fuel consumption rate in gallons per mile

        AirplaneTestModel airplaneStub = new AirplaneTestModel(fuelConsumptionRate);
        FuelConsumptionCalculator calculator = new FuelConsumptionCalculator(airplaneStub, distance, weatherFactor);

        double actualFuelConsumption = calculator.call();
        double expectedFuelConsumption = distance / fuelConsumptionRate * weatherFactor;

        assertEquals("The calculated fuel consumption should match the expected value.",
                expectedFuelConsumption, actualFuelConsumption, 0.01); // Allow for a small margin of error
    }
}
