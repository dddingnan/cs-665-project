package edu.bu.met.cs665.airplane;

import edu.bu.met.cs665.exception.InvalidDataException;
import org.junit.Assert;
import org.junit.Test;

public class Boeing747Test {

    @Test
    public void testAirplaneCreation() throws InvalidDataException {
        Boeing747 boeing747 = new Boeing747("Boeing747", 10000, 400, 20, 950);
        Assert.assertEquals("Boeing747", boeing747.getName());
        Assert.assertEquals(10000, boeing747.getRange(), 0.0);
        Assert.assertEquals(400, boeing747.getFuelCapacity(), 0.0);
        Assert.assertEquals(20, boeing747.getFuelBurnRate(), 0.0);
        Assert.assertEquals(950, boeing747.getSpeed(), 0.0);
    }

    @Test
    public void testGetFuelConsumption() throws InvalidDataException {
        Boeing747 boeing747 = new Boeing747("Boeing747", 10000, 400, 20, 950);
        double expectedFuelConsumption = 10000 / (400 / (20 * 0.95)); // As per the formula in getFuelConsumption
        Assert.assertEquals(expectedFuelConsumption, boeing747.getFuelConsumption(), 0.0);
    }
}
