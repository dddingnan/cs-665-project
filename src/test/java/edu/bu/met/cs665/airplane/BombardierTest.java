package edu.bu.met.cs665.airplane;

import edu.bu.met.cs665.exception.InvalidDataException;
import org.junit.Assert;
import org.junit.Test;

public class BombardierTest {

    @Test
    public void testAirplaneCreation() throws InvalidDataException {
        Bombardier bombardier = new Bombardier("Bombardier", 8000, 300, 20, 750);
        Assert.assertEquals("Bombardier", bombardier.getName());
        Assert.assertEquals(8000, bombardier.getRange(), 0.0);
        Assert.assertEquals(300, bombardier.getFuelCapacity(), 0.0);
        Assert.assertEquals(20, bombardier.getFuelBurnRate(), 0.0);
        Assert.assertEquals(750, bombardier.getSpeed(), 0.0);
    }

    @Test
    public void testGetFuelConsumption() throws InvalidDataException {
        Bombardier bombardier = new Bombardier("Bombardier", 8000, 300, 20, 750);
        double expectedFuelConsumption = 8000 / (300 / (20 * 0.35)); // As per the formula in getFuelConsumption
        Assert.assertEquals(expectedFuelConsumption, bombardier.getFuelConsumption(), 0.0);
    }
}
