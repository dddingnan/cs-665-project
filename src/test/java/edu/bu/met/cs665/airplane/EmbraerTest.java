package edu.bu.met.cs665.airplane;

import edu.bu.met.cs665.exception.InvalidDataException;
import org.junit.Assert;
import org.junit.Test;

public class EmbraerTest {

    @Test
    public void testAirplaneCreation() throws InvalidDataException {
        Embraer embraer = new Embraer("Embraer", 5000, 200, 15, 600);
        Assert.assertEquals("Embraer", embraer.getName());
        Assert.assertEquals(5000, embraer.getRange(), 0.0);
        Assert.assertEquals(200, embraer.getFuelCapacity(), 0.0);
        Assert.assertEquals(15, embraer.getFuelBurnRate(), 0.0);
        Assert.assertEquals(600, embraer.getSpeed(), 0.0);
    }

    @Test
    public void testGetFuelConsumption() throws InvalidDataException {
        Embraer embraer = new Embraer("Embraer", 5000, 200, 15, 600);
        double expectedFuelConsumption = 5000 / (200 / (15 * 0.1)); // As per the formula in getFuelConsumption
        Assert.assertEquals(expectedFuelConsumption, embraer.getFuelConsumption(), 0.0);
    }
}
