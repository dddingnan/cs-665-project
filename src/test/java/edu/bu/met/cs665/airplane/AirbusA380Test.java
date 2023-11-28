package edu.bu.met.cs665.airplane;

import edu.bu.met.cs665.exception.InvalidDataException;
import org.junit.Assert;
import org.junit.Test;

public class AirbusA380Test {

    @Test
    public void testAirplaneCreation() throws InvalidDataException {
        AirbusA380 airbusA380 = new AirbusA380("AirbusA380", 8000, 300, 15, 900);
        Assert.assertEquals("AirbusA380", airbusA380.getName());
        Assert.assertEquals(8000, airbusA380.getRange(), 0.0);
        Assert.assertEquals(300, airbusA380.getFuelCapacity(), 0.0);
        Assert.assertEquals(15, airbusA380.getFuelBurnRate(), 0.0);
        Assert.assertEquals(900, airbusA380.getSpeed(), 0.0);
    }

    @Test
    public void testGetFuelConsumption() throws InvalidDataException {
        AirbusA380 airbusA380 = new AirbusA380("AirbusA380", 8000, 300, 15, 900);
        double expectedFuelConsumption = (8000 * 1.1) / (300 / 15); // As per the formula in getFuelConsumption
        Assert.assertEquals(expectedFuelConsumption, airbusA380.getFuelConsumption(), 0.0);
    }
}
