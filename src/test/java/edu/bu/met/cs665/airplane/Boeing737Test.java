package edu.bu.met.cs665.airplane;

import edu.bu.met.cs665.exception.InvalidDataException;
import org.junit.Assert;
import org.junit.Test;

public class Boeing737Test {

    @Test
    public void testAirplaneCreation() throws InvalidDataException {
        Boeing737 boeing737 = new Boeing737("Boeing737", 8000, 300, 15, 900);
        Assert.assertEquals("Boeing737", boeing737.getName());
        Assert.assertEquals(8000, boeing737.getRange(), 0.0);
        Assert.assertEquals(300, boeing737.getFuelCapacity(), 0.0);
        Assert.assertEquals(15, boeing737.getFuelBurnRate(), 0.0);
        Assert.assertEquals(900, boeing737.getSpeed(), 0.0);
    }

    @Test
    public void testGetFuelConsumption() throws InvalidDataException {
        Boeing737 boeing737 = new Boeing737("Boeing737", 8000, 300, 15, 900);
        double expectedFuelConsumption = 8000 / (300 / (15 * 0.87)); // As per the formula in getFuelConsumption
        Assert.assertEquals(expectedFuelConsumption, boeing737.getFuelConsumption(), 0.0);
    }
}
