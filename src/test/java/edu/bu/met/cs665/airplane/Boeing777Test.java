package edu.bu.met.cs665.airplane;

import edu.bu.met.cs665.exception.InvalidDataException;
import org.junit.Assert;
import org.junit.Test;

public class Boeing777Test {

    @Test
    public void testAirplaneCreation() throws InvalidDataException {
        Boeing777 boeing777 = new Boeing777("Boeing777", 12000, 500, 25, 900);
        Assert.assertEquals("Boeing777", boeing777.getName());
        Assert.assertEquals(12000, boeing777.getRange(), 0.0);
        Assert.assertEquals(500, boeing777.getFuelCapacity(), 0.0);
        Assert.assertEquals(25, boeing777.getFuelBurnRate(), 0.0);
        Assert.assertEquals(900, boeing777.getSpeed(), 0.0);
    }

    @Test
    public void testGetFuelConsumption() throws InvalidDataException {
        Boeing777 boeing777 = new Boeing777("Boeing777", 12000, 500, 25, 900);
        double expectedFuelConsumption = 12000 / (500 / (25 * 0.98)); // As per the formula in getFuelConsumption
        Assert.assertEquals(expectedFuelConsumption, boeing777.getFuelConsumption(), 0.0);
    }
}
