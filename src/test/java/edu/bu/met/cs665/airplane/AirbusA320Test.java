package edu.bu.met.cs665.airplane;

import edu.bu.met.cs665.exception.InvalidDataException;
import org.junit.Assert;
import org.junit.Test;

public class AirbusA320Test {

    @Test
    public void testAirplaneCreation() throws InvalidDataException {
        AirbusA320 airbusA320 = new AirbusA320("AirbusA320", 5000, 200, 10, 800);
        Assert.assertEquals("AirbusA320", airbusA320.getName());
        Assert.assertEquals(5000, airbusA320.getRange(), 0.0);
        Assert.assertEquals(200, airbusA320.getFuelCapacity(), 0.0);
        Assert.assertEquals(10, airbusA320.getFuelBurnRate(), 0.0);
        Assert.assertEquals(800, airbusA320.getSpeed(), 0.0);
    }

    @Test
    public void testGetFuelConsumption() throws InvalidDataException {
        AirbusA320 airbusA320 = new AirbusA320("AirbusA320", 5000, 200, 10, 800);
        double expectedFuelConsumption = (5000 * 1.08) / (200 / 10); // As per the formula in getFuelConsumption
        Assert.assertEquals(expectedFuelConsumption, airbusA320.getFuelConsumption(), 0.0);
    }
}